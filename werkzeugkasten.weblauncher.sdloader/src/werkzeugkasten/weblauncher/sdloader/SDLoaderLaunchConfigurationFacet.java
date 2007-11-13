package werkzeugkasten.weblauncher.sdloader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;

import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.launcher.impl.AbstractLaunchConfigurationFacet;
import werkzeugkasten.weblauncher.sdloader.launch.SDLoaderLaunchConfigurationBuilder;

public class SDLoaderLaunchConfigurationFacet extends
		AbstractLaunchConfigurationFacet {

	public void addLibrary(IJavaProject project) throws CoreException {
		// TODO Auto-generated method stub

	}

	public LaunchConfigurationBuilder getBuilder() {
		return new SDLoaderLaunchConfigurationBuilder();
	}

	public boolean hasLibrary(IJavaProject project) throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeLibrary(IJavaProject project) throws CoreException {
		// TODO Auto-generated method stub

	}

}
