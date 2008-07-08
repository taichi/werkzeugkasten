package werkzeugkasten.webappnls.gen;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
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
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.ui.CodeGeneration;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import werkzeugkasten.common.jdt.JavaElementUtil;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.webappnls.Activator;
import werkzeugkasten.webappnls.Constants;
import werkzeugkasten.webappnls.ResourceGenerator;

public class MultiLocaleStrings implements ResourceGenerator {

	@Override
	public void generateFrom(IResource resource, IProgressMonitor monitor) {
		try {
			String destPath = resource
					.getPersistentProperty(Constants.GENERATION_DEST);
			IPath path = null;
			if (StringUtil.isEmpty(destPath)) {
				IPath p = resource.getFullPath();
				p = p.removeFileExtension();
				String s = p.lastSegment();
				s = s.substring(0, s.lastIndexOf('_') - 1);
				p = p.removeLastSegments(1).append(s);
				path = p.addFileExtension("java");
			} else {
				path = new Path(destPath);
			}
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IResource dest = root.findMember(path);
			IJavaElement element = JavaCore.create(dest);
			ICompilationUnit unit = JavaElementUtil.to(element);
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
			merge(props, type, monitor);
		} catch (OperationCanceledException e) {
			throw e;
		} catch (Exception e) {
			Activator.log(e);
		}
	}

	protected ICompilationUnit createNewCU(IResource resource, IPath path)
			throws Exception {
		IJavaProject javap = JavaElementUtil.getJavaProject(path);
		IPackageFragment pf = javap.findPackageFragment(path
				.removeLastSegments(1));

		ICompilationUnit unit = pf.createCompilationUnit(path.lastSegment(),
				"", true, null);
		unit.becomeWorkingCopy(null);

		String name = path.removeFileExtension().lastSegment();
		String ln = ProjectUtil.getLineDelimiterPreference(javap.getProject());

		IBuffer buffer = unit.getBuffer();
		buffer.setContents(createCUContent(unit, name, ln));

		// unit.createPackageDeclaration(pf.getElementName(), null);
		unit.createImport("java.util.HashMap", null, null);
		unit.createImport("java.util.Locale", null, null);
		unit.createImport("java.util.Map", null, null);
		unit.createImport("java.util.ResourceBundle", null, null);

		IType type = unit.findPrimaryType();
		type
				.createField(
						"protected Map<Locale, ResourceBundle> bundles = new HashMap<Locale, ResourceBundle>();",
						null, true, null);
		type.createMethod("public void add(ResourceBundle bundle) {"
				+ "this.bundles.put(bundle.getLocale(), bundle);" + "}", null,
				true, null);
		type
				.createMethod(
						"public String getMessage(Locale locale, String key) {"
								+ "ResourceBundle rb = this.bundles.get(locale);"
								+ "return rb != null ? rb.getString(key) : null;"
								+ "}", null, true, null);
		type.createInitializer("public " + name + "(){"
				+ "add(ResourceBundle.getBundle(" + name
				+ ".class.getName()));" + "}", null, null);

		CodeFormatter formatter = ToolFactory.createCodeFormatter(javap
				.getOptions(true));
		String contents = buffer.getContents();
		TextEdit edit = formatter.format(CodeFormatter.K_COMPILATION_UNIT,
				contents, 0, buffer.getLength(), 0, ln);
		if (edit != null) {
			IDocument doc = new Document(contents);
			edit.apply(doc);
			buffer.setContents(doc.get());
		}

		unit.commitWorkingCopy(true, null);
		return unit;
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
		stb.append("{");
		stb.append(CodeGeneration.getTypeBody(
				CodeGeneration.CLASS_BODY_TEMPLATE_ID, unit, name, ln));
		stb.append("}");
		return stb.toString();
	}

	protected void merge(Properties props, IType type, IProgressMonitor monitor)
			throws Exception {
		IMethod[] methods = type.getMethods();
		Set<String> methodNames = new HashSet<String>();
		for (IMethod m : methods) {
			String name = m.getElementName();
			methodNames.add(name);
		}

		for (String s : props.stringPropertyNames()) {
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			if (methodNames.contains(s) == false) {
				String contains = createMethodContent(type, s);
				type.createMethod(contains, null, true, null);
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
