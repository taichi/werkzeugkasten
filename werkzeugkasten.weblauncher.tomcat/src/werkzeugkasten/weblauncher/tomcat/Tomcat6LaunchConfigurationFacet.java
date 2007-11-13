package werkzeugkasten.weblauncher.tomcat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;

import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.launcher.impl.AbstractLaunchConfigurationFacet;
import werkzeugkasten.weblauncher.tomcat.launch.TomcatLaunchConfigurationBuilder;

public class Tomcat6LaunchConfigurationFacet extends
		AbstractLaunchConfigurationFacet {

	@Override
	public boolean hasLibrary(IJavaProject project) throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addLibrary(IJavaProject project) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeLibrary(IJavaProject project) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public LaunchConfigurationBuilder getBuilder() {
		return new TomcatLaunchConfigurationBuilder();
	}

}
