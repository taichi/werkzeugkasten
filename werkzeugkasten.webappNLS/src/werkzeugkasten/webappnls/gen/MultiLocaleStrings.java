package werkzeugkasten.webappnls.gen;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.ui.CodeGeneration;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import werkzeugkasten.common.jdt.ClasspathEntryUtil;
import werkzeugkasten.common.jdt.JavaElementUtil;
import werkzeugkasten.common.jdt.TypeUtil;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.webappnls.Activator;
import werkzeugkasten.webappnls.Constants;
import werkzeugkasten.webappnls.ResourceGenerator;

public class MultiLocaleStrings implements ResourceGenerator {

	@Override
	public void generateFrom(IResource resource, IProgressMonitor monitor) {
		ICompilationUnit unit = null;
		try {
			Set<IPath> locs = ClasspathEntryUtil.getOutputLocations(JavaCore
					.create(resource.getProject()));
			for (IPath p : locs) {
				if (p.isPrefixOf(resource.getFullPath())) {
					return;
				}
			}
			IPath path = toDestPath(resource);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IResource dest = root.findMember(path);
			IJavaElement element = JavaCore.create(dest);
			unit = JavaElementUtil.to(element);
			if (unit == null || unit.exists() == false) {
				if (monitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				unit = createNewCU(resource, path);
			}
			IType type = unit.findPrimaryType();
			Properties props = new Properties();
			props.load(new BufferedInputStream(new FileInputStream(new File(
					resource.getLocationURI()))));
			unit.becomeWorkingCopy(null);
			merge(props, type, monitor);

			String ln = ProjectUtil.getLineDelimiterPreference(resource
					.getProject());
			IBuffer buffer = unit.getBuffer();
			formatCU(type.getJavaProject(), ln, buffer);
			unit.commitWorkingCopy(true, null);
		} catch (OperationCanceledException e) {
			discardWorkingCopy(unit);
			throw e;
		} catch (Exception e) {
			discardWorkingCopy(unit);
			Activator.log(e);
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
		String destPath = resource
				.getPersistentProperty(Constants.GENERATION_DEST);
		if (StringUtil.isEmpty(destPath)) {
			IPath p = resource.getFullPath();
			p = p.removeFileExtension();
			String s = p.lastSegment();
			int index = s.lastIndexOf('_') - 1;
			if (0 < index) {
				s = s.substring(0, index);
			}
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
		ICompilationUnit unit = null;
		try {
			unit = pf.createCompilationUnit(path.lastSegment(), "", true, null);
			unit.becomeWorkingCopy(null);
			String name = path.removeFileExtension().lastSegment();
			String ln = ProjectUtil.getLineDelimiterPreference(javap
					.getProject());

			IBuffer buffer = unit.getBuffer();
			buffer.setContents(createCUContent(unit, name, ln));

			unit.createImport("java.util.HashMap", null, null);
			unit.createImport("java.util.Locale", null, null);
			unit.createImport("java.util.Map", null, null);
			unit.createImport("java.util.ResourceBundle", null, null);

			IType type = unit.findPrimaryType();
			type
					.createField(
							"protected Map<Locale, ResourceBundle> bundles = new HashMap<Locale, ResourceBundle>();",
							null, true, null);
			type.createMethod("public " + name + "() {"
					+ "add(ResourceBundle.getBundle(getClass().getName()));}",
					null, true, null);
			type.createMethod("public void add(ResourceBundle bundle) {"
					+ "this.bundles.put(bundle.getLocale(), bundle);" + "}",
					null, true, null);
			type.createMethod(
					"public String getMessage(Locale locale, String key) {"
							+ "ResourceBundle rb = this.bundles.get(locale);"
							+ "return rb != null ? rb.getString(key) : null;"
							+ "}", null, true, null);

			formatCU(javap, ln, buffer);
			unit.commitWorkingCopy(true, null);
			return unit;
		} catch (Exception e) {
			discardWorkingCopy(unit);
			throw e;
		}
	}

	protected void formatCU(IJavaProject javap, String ln, IBuffer buffer)
			throws BadLocationException {
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
		stb.append(" {");
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

	protected void merge(Properties props, IType type, IProgressMonitor monitor)
			throws Exception {
		IMethod[] methods = type.getMethods();
		Set<String> methodNames = new HashSet<String>();
		for (IMethod m : methods) {
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			String[] types = m.getParameterTypes();
			if (types != null
					&& types.length == 1
					&& isLocale.matcher(
							TypeUtil.getResolvedTypeName(types[0], type))
							.matches()) {
				String name = m.getElementName();
				if (props.contains(name) == false && m.exists()) {
					m.delete(true, null);
				} else {
					methodNames.add(name);
				}
			}
		}

		for (String s : props.stringPropertyNames()) {
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			if (methodNames.contains(s) == false) {
				String contents = createMethodContent(type, s);
				type.createMethod(contents, null, true, null);
			}
		}
	}

	protected String createMethodContent(IType type, String name) {
		StringBuilder stb = new StringBuilder();
		stb.append("public String ");
		stb.append(name);
		stb.append("(Locale l) {\r\nreturn getMessage(l, \"");
		stb.append(name);
		stb.append("\");}");
		return stb.toString();
	}

}
