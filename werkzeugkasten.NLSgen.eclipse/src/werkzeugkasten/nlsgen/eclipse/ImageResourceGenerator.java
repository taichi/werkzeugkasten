package werkzeugkasten.nlsgen.eclipse;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.jdt.ClasspathEntryUtil;
import werkzeugkasten.common.jdt.JavaElementUtil;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.resource.ResourceUtil;

public class ImageResourceGenerator extends StringResourceGenerator {

	@Override
	public boolean verifyRuntime(IJavaProject javap) {
		try {
			IType type = javap.findType(ImageLoader.class.getName());
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
					.getEntry("nlsgen-runtime-eclipse.jar"));
			ResourceUtil.copyFile(container, bundle
					.getEntry("nlsgen-runtime-eclipse.src.jar"));

			IPath path = container.getFullPath();
			IJavaProject javap = JavaCore.create(container.getProject());
			IClasspathEntry ce = JavaCore.newLibraryEntry(path
					.append("nlsgen-runtime-eclipse.jar"), path
					.append("nlsgen-runtime-eclipse.src.jar"), new Path("."));
			ClasspathEntryUtil.addClasspathEntry(javap, ce);
		} catch (Exception e) {
			Activator.log(e);
		}
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
		unit.createImport("werkzeugkasten.nlsgen.eclipse.ImageLoader", null,
				null);
		unit.createImport("org.eclipse.jface.resource.ImageDescriptor", null,
				null);
		unit.createImport("org.eclipse.swt.graphics.Image", null, null);

		String activator = getActivatorClass(resource, unit);

		IType type = unit.findPrimaryType();
		type.createInitializer("static {" + "ImageLoader.load(" + activator
				+ ".getDefault(), " + name + ".class,\"images\");" + "}", null,
				null);

		formatCU(javap, ln, unit);
		return unit;
	}

	@Override
	protected String createFiledContent(IType type, String name) {
		StringBuilder stb = new StringBuilder();
		stb.append("public static ImageDescriptor ");
		stb.append(name);
		stb.append(";");
		return stb.toString();
	}
}
