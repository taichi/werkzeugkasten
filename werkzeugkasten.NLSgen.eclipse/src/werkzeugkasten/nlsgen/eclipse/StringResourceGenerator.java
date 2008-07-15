package werkzeugkasten.nlsgen.eclipse;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.pde.core.plugin.IPluginModel;
import org.eclipse.pde.core.plugin.PluginRegistry;

import werkzeugkasten.common.jdt.JavaElementUtil;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.util.StringUtil;
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
		if (pf == null || pf.exists() == false) {
			pf = createNewPackage(javap, path);
		}

		if (pf == null) {
			return null;
		}

		ICompilationUnit unit = pf.createCompilationUnit(path.lastSegment(),
				"", true, null);
		String name = path.removeFileExtension().lastSegment();
		String ln = ProjectUtil.getLineDelimiterPreference(javap.getProject());

		unit.createType(createCUContent(unit, name, ln), null, false, null);
		unit.createImport("org.eclipse.osgi.util.NLS", null, null);

		String activator = getActivatorClass(resource, unit);

		IType type = unit.findPrimaryType();
		type.createInitializer("static {" + "Object o = " + activator
				+ ".getDefault().getBundle()"
				+ ".getHeaders().get(\"Bundle-Localization\");"
				+ "String s = o == null ? \"plugin\" : o.toString();"
				+ "NLS.initializeMessages(s, " + name + ".class);" + "}", null,
				null);

		formatCU(javap, ln, unit);
		return unit;
	}

	protected String getActivatorClass(IResource resource, ICompilationUnit unit)
			throws JavaModelException {
		String activator = "Activator";
		IPluginModel model = AdaptableUtil.to(PluginRegistry.findModel(resource
				.getProject()), IPluginModel.class);
		if (model != null) {
			String s = model.getPlugin().getClassName();
			if (StringUtil.isEmpty(s) == false) {
				unit.createImport(s, null, null);
				int index = s.lastIndexOf('.');
				if (1 < index) {
					activator = s.substring(index + 1);
				} else {
					activator = s;
				}
			}
		}
		return activator;
	}

}
