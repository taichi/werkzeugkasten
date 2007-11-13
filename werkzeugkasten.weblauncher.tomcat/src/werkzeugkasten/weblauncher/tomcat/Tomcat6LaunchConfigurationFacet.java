package werkzeugkasten.weblauncher.tomcat;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import werkzeugkasten.common.jdt.ClasspathEntryUtil;
import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.launcher.impl.AbstractLaunchConfigurationFacet;
import werkzeugkasten.weblauncher.tomcat.launch.TomcatLaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.tomcat.variable.Variable;

public class Tomcat6LaunchConfigurationFacet extends
		AbstractLaunchConfigurationFacet {

	public boolean hasLibrary(IJavaProject project) throws CoreException {
		Map<IPath, IClasspathEntry> m = ClasspathEntryUtil
				.toClasspathMap(project);
		return m.containsKey(Variable.SERVLET_API_2_5)
				&& m.containsKey(Variable.JSP_API_2_1);
	}

	public void addLibrary(IJavaProject project) throws CoreException {
		ClasspathEntryUtil.addClasspathEntry(project, JavaCore
				.newVariableEntry(Variable.SERVLET_API_2_5, null,
						new Path("./")));
		ClasspathEntryUtil.addClasspathEntry(project, JavaCore
				.newVariableEntry(Variable.JSP_API_2_1, null, new Path("./")));
	}

	public void removeLibrary(IJavaProject project) throws CoreException {
		ClasspathEntryUtil.removeClasspathEntry(project,
				Variable.SERVLET_API_2_5);
		ClasspathEntryUtil.removeClasspathEntry(project, Variable.JSP_API_2_1);
	}

	public LaunchConfigurationBuilder getBuilder() {
		return new TomcatLaunchConfigurationBuilder();
	}

}
