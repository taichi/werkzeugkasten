package werkzeugkasten.nlsgen.gen;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.ui.CodeGeneration;

import werkzeugkasten.common.jdt.JavaElementUtil;
import werkzeugkasten.common.jdt.TypeUtil;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.nlsgen.Activator;
import werkzeugkasten.nlsgen.SingleLocaleStrings;

public class SingleLocaleStringsGenerator extends MultiLocaleStringsGenerator {

	@Override
	public boolean verifyRuntime(IJavaProject javap) {
		try {
			IType type = javap.findType(SingleLocaleStrings.class.getName());
			return type != null && type.exists();
		} catch (CoreException e) {
			Activator.log(e);
			return false;
		}
	}

	@Override
	protected ICompilationUnit createNewCU(IResource resource, IPath path)
			throws Exception {
		IJavaProject javap = JavaElementUtil.getJavaProject(path);
		IPackageFragment pf = javap.findPackageFragment(path
				.removeLastSegments(1));
		ICompilationUnit unit = pf.createCompilationUnit(path.lastSegment(),
				"", true, null);
		String name = path.removeFileExtension().lastSegment();
		String ln = ProjectUtil.getLineDelimiterPreference(javap.getProject());

		unit.createType(createCUContent(unit, name, ln), null, false, null);
		unit.createImport("werkzeugkasten.nlsgen.SingleLocaleStrings", null,
				null);

		IType type = unit.findPrimaryType();
		type.createInitializer("static {" + "Class<?> clazz = "
				+ type.getElementName() + ".class;"
				+ "SingleLocaleStrings.load(clazz);" + "}", null, null);

		formatCU(javap, ln, unit);
		return unit;
	}

	@Override
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

	protected static final Pattern isString = Pattern
			.compile("(java\\.lang\\.)?String");

	@Override
	protected void merge(Properties props, IType type, IProgressMonitor monitor)
			throws Exception {
		IField[] fields = type.getFields();
		Set<String> fieldNames = new HashSet<String>();
		for (IField f : fields) {
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			String t = f.getTypeSignature();

			if (isString.matcher(TypeUtil.getResolvedTypeName(t, type))
					.matches()) {
				String name = f.getElementName();
				if (props.containsKey(name) == false && f.exists()) {
					f.delete(true, null);
				} else {
					fieldNames.add(name);
				}
			}
		}

		for (String s : props.stringPropertyNames()) {
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			if (fieldNames.contains(s) == false) {
				String contents = createFiledContent(type, s);
				type.createField(contents, null, true, null);
			}
		}

	}

	protected String createFiledContent(IType type, String name) {
		StringBuilder stb = new StringBuilder();
		stb.append("public static String ");
		stb.append(name);
		stb.append(";");
		return stb.toString();
	}
}
