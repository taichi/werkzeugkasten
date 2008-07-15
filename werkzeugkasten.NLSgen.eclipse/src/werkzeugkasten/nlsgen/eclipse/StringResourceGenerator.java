package werkzeugkasten.nlsgen.eclipse;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;

import werkzeugkasten.common.jdt.JavaElementUtil;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.nlsgen.gen.SingleLocaleStringsGenerator;

public class StringResourceGenerator extends SingleLocaleStringsGenerator {

	@Override
	public boolean verifyRuntime(IJavaProject javap) {
		return true;
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
		unit.createImport("org.eclipse.osgi.util.NLS", null, null);

		IType type = unit.findPrimaryType();
		type.createInitializer("static {"
				+ "Object o = Activator.getDefault().getBundle()"
				+ ".getHeaders().get(\"Bundle-Localization\");"
				+ "String s = o == null ? \"plugin\" : o.toString();"
				+ "NLS.initializeMessages(s, " + name + ".class);" + "}", null,
				null);

		formatCU(javap, ln, unit);
		return unit;
	}
}
