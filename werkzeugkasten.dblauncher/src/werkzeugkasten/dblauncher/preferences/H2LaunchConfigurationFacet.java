package werkzeugkasten.dblauncher.preferences;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import werkzeugkasten.common.jdt.ClasspathEntryUtil;
import werkzeugkasten.dblauncher.launch.H2ConfigurationBuilder;
import werkzeugkasten.dblauncher.variable.Variable;
import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.launcher.LaunchConfigurationFacet;
import werkzeugkasten.launcher.LibraryConfigurator;
import werkzeugkasten.launcher.impl.AbstractConfigurationFacet;

public class H2LaunchConfigurationFacet extends AbstractConfigurationFacet
		implements LibraryConfigurator, LaunchConfigurationFacet {

	public boolean hasLibrary(IJavaProject project) throws CoreException {
		return ClasspathEntryUtil.hasClasspathEntry(project, Variable.LIB);
	}

	public void addLibrary(IJavaProject project) throws CoreException {
		ClasspathEntryUtil.addClasspathEntry(project, JavaCore
				.newVariableEntry(Variable.LIB, Variable.SRC, new Path("./")));
	}

	public void removeLibrary(IJavaProject project) throws CoreException {
		ClasspathEntryUtil.removeClasspathEntry(project, Variable.LIB);
	}

	public LaunchConfigurationBuilder getBuilder() {
		return new H2ConfigurationBuilder();
	}

}
