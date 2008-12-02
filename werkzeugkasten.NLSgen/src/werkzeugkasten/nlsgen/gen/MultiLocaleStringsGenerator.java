package werkzeugkasten.nlsgen.gen;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.ui.CodeGeneration;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.jdt.ClasspathEntryUtil;
import werkzeugkasten.common.jdt.JavaElementUtil;
import werkzeugkasten.common.jdt.TypeUtil;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.resource.ResourceUtil;
import werkzeugkasten.common.ui.ProgressMonitorUtil;
import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.nlsgen.Activator;
import werkzeugkasten.nlsgen.Constants;
import werkzeugkasten.nlsgen.MultiLocaleStrings;
import werkzeugkasten.nlsgen.ResourceGenerator;
import werkzeugkasten.nlsgen.nls.Strings;

public class MultiLocaleStringsGenerator implements ResourceGenerator {

	@Override
	public boolean verifyRuntime(IJavaProject javap) {
		try {
			IType type = javap.findType(MultiLocaleStrings.class.getName());
			return type != null && type.exists();
		} catch (CoreException e) {
			Activator.log(e);
			return false;
		}
	}

	@Override
	public void addRuntime(IContainer container) {
		try {
			Bundle bundle = Activator.getDefault().getBundle();
			ResourceUtil.copyFile(container, bundle
					.getEntry("nlsgen-runtime.jar"));
			ResourceUtil.copyFile(container, bundle
					.getEntry("nlsgen-runtime.src.jar"));

			IPath path = container.getFullPath();
			IJavaProject javap = JavaCore.create(container.getProject());
			IClasspathEntry ce = JavaCore.newLibraryEntry(path
					.append("nlsgen-runtime.jar"), path
					.append("nlsgen-runtime.src.jar"), new Path("."));
			ClasspathEntryUtil.addClasspathEntry(javap, ce);
		} catch (Exception e) {
			Activator.log(e);
		}
	}

	@Override
	public void generateFrom(IFile properties, IProgressMonitor monitor) {
		try {
			monitor = ProgressMonitorUtil.care(monitor);
			monitor.beginTask(Strings.GENERATE_CLASSES, 5);
			Set<IPath> locs = ClasspathEntryUtil.getOutputLocations(JavaCore
					.create(properties.getProject()));
			for (IPath p : locs) {
				if (p.isPrefixOf(properties.getFullPath())) {
					return;
				}
			}
			ProgressMonitorUtil.isCanceled(monitor, 1);

			IPath path = toDestPath(properties);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IResource dest = root.findMember(path);
			IJavaElement element = JavaCore.create(dest);
			ICompilationUnit unit = JavaElementUtil.to(element);
			if (unit == null || unit.exists() == false) {
				ProgressMonitorUtil.isCanceled(monitor, 1);
				unit = createNewCU(properties, path);
			}

			if (unit == null) {
				return;
			}

			ProgressMonitorUtil.isCanceled(monitor, 1);

			IType type = unit.findPrimaryType();
			Properties props = loadProperties(properties);

			merge(props, type, new SubProgressMonitor(monitor, 1));

			String ln = ProjectUtil.getLineDelimiterPreference(properties
					.getProject());
			formatCU(type.getJavaProject(), ln, unit);

			ProgressMonitorUtil.isCanceled(monitor, 1);
		} catch (OperationCanceledException e) {
			throw e;
		} catch (Exception e) {
			Activator.log(e);
		} finally {
			monitor.done();
		}
	}

	private Properties loadProperties(final IFile properties) throws Exception {
		InputStream in = null;
		try {
			in = new BufferedInputStream(properties.getContents());
			Properties props = new Properties();
			props.load(in);
			return props;
		} finally {
			Streams.close(in);
		}
	}

	protected void discardWorkingCopy(ICompilationUnit unit) {
		if (unit != null && unit.isWorkingCopy()) {
			try {
				unit.discardWorkingCopy();
			} catch (JavaModelException jme) {
				Activator.log(jme);
			}
		}
	}

	protected IPath toDestPath(IResource resource) throws CoreException {
		IPath path = null;
		ScopedPreferenceStore store = new ScopedPreferenceStore(
				new ProjectScope(resource.getProject()), Constants.ID_PLUGIN);
		String destPath = store.getString(Constants.GENERATION_DEST(resource));

		if (StringUtil.isEmpty(destPath)) {
			IPath p = resource.getFullPath();
			p = p.removeFileExtension();
			String s = p.lastSegment();
			int index = s.lastIndexOf('_') - 1;
			if (0 < index) {
				s = s.substring(0, index);
			}
			s = StringUtil.toCamelCase(s);
			p = p.removeLastSegments(1).append(s);
			path = p.addFileExtension("java");
		} else {
			path = new Path(destPath);
		}
		return path;
	}

	protected ICompilationUnit createNewCU(IResource resource, IPath path)
			throws Exception {
		IJavaProject javap = JavaElementUtil.getJavaProject(path);
		IPackageFragment pf = javap.findPackageFragment(path
				.removeLastSegments(1));
		if (pf == null || pf.exists() == false) {
			pf = createNewPackage(javap, path);
		}

		if (pf == null) {
			return null;
		}

		ICompilationUnit unit = pf.createCompilationUnit(path.lastSegment(),
				"", false, null);
		String name = path.removeFileExtension().lastSegment();
		String ln = ProjectUtil.getLineDelimiterPreference(javap.getProject());

		unit.createType(createCUContent(unit, name, ln), null, false, null);

		unit.createImport("java.util.Locale", null, null);
		unit.createImport("java.util.ResourceBundle", null, null);
		unit.createImport("werkzeugkasten.nlsgen.MultiLocaleStrings", null,
				null);

		IType type = unit.findPrimaryType();
		type.createMethod("public " + name + "() {"
				+ "add(ResourceBundle.getBundle(getClass().getName()));}",
				null, false, null);

		formatCU(javap, ln, unit);

		return unit;
	}

	protected IPackageFragment createNewPackage(IJavaProject javap, IPath path)
			throws JavaModelException {
		for (IPackageFragmentRoot root : javap.getPackageFragmentRoots()) {
			IPath p = root.getPath();
			if (p.isPrefixOf(path)) {
				p = path.removeFirstSegments(p.segmentCount())
						.removeLastSegments(1);
				return root.createPackageFragment(p.toString()
						.replace('/', '.'), false, null);
			}
		}
		return null;
	}

	protected void formatCU(IJavaProject javap, String ln, ICompilationUnit unit)
			throws Exception {
		ICompilationUnit copy = unit.getWorkingCopy(null);
		try {
			IBuffer buffer = copy.getBuffer();
			CodeFormatter formatter = ToolFactory.createCodeFormatter(javap
					.getOptions(true));
			String contents = buffer.getContents();
			TextEdit edit = formatter.format(CodeFormatter.K_COMPILATION_UNIT,
					contents, 0, buffer.getLength(), 0, ln);
			if (edit != null) {
				IDocument doc = new Document(contents);
				edit.apply(doc);
				buffer.replace(0, contents.length(), doc.get());
			}
			copy.commitWorkingCopy(false, null);
		} catch (Exception e) {
			copy.discardWorkingCopy();
		}
	}

	protected String createCUContent(ICompilationUnit unit, String name,
			String ln) throws Exception {
		String fileComment = CodeGeneration.getFileComment(unit, ln);
		String typeComment = CodeGeneration.getTypeComment(unit, unit
				.getParent().getElementName()
				+ "." + name, ln);

		String content = CodeGeneration
				.getCompilationUnitContent(unit, fileComment, typeComment,
						createTypeContent(unit, name, ln), ln);

		return content;
	}

	protected String createTypeContent(ICompilationUnit unit, String name,
			String ln) throws Exception {
		StringBuilder stb = new StringBuilder();
		stb.append("public class ");
		stb.append(name);
		stb.append(" extends MultiLocaleStrings {");
		String body = CodeGeneration.getTypeBody(
				CodeGeneration.CLASS_BODY_TEMPLATE_ID, unit, name, ln);
		if (StringUtil.isEmpty(body) == false) {
			stb.append(body);
		}
		stb.append("}");
		return stb.toString();
	}

	protected static final Pattern isLocale = Pattern
			.compile("(java\\.util\\.)?Locale");

	protected static final Pattern isObjectArray = Pattern
			.compile("(java\\.lang\\.)?Object\\[\\]");

	protected void merge(Properties props, IType type, IProgressMonitor monitor)
			throws Exception {
		monitor = ProgressMonitorUtil.care(monitor);
		monitor.beginTask(Strings.MODIFY_CLASSES, IProgressMonitor.UNKNOWN);

		IMethod[] methods = type.getMethods();
		Set<String> methodNames = new HashSet<String>();
		for (IMethod m : methods) {
			ProgressMonitorUtil.isCanceled(monitor, 1);
			String[] types = m.getParameterTypes();
			if (types != null
					&& types.length == 2
					&& isLocale.matcher(
							TypeUtil.getResolvedTypeName(types[0], type))
							.matches()
					&& isObjectArray.matcher(
							TypeUtil.getResolvedTypeName(types[1], type))
							.matches()) {
				String name = m.getElementName();
				if (props.containsKey(name) == false && m.exists()) {
					m.delete(false, null);
				} else {
					methodNames.add(name);
				}
			}
		}

		ProgressMonitorUtil.isCanceled(monitor, 1);

		for (String s : props.stringPropertyNames()) {
			ProgressMonitorUtil.isCanceled(monitor, 1);
			if (methodNames.contains(s) == false && isJavaIdentifier(s)) {
				String contents = createMethodContent(type, s);
				type.createMethod(contents, null, false, null);
			}
		}
	}

	protected boolean isJavaIdentifier(String s) {
		return StringUtil.isJavaIdentifier(s);
	}

	protected String createMethodContent(IType type, String name) {
		StringBuilder stb = new StringBuilder();
		stb.append("public String ");
		stb.append(name);
		stb.append("(Locale l, Object...objects) { return getMessage(l, \"");
		stb.append(name);
		stb.append("\",objects);}");
		return stb.toString();
	}

}
