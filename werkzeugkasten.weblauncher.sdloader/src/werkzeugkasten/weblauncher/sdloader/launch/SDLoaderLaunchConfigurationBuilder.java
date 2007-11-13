package werkzeugkasten.weblauncher.sdloader.launch;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import werkzeugkasten.launcher.LaunchConfigurationBuilder;

public class SDLoaderLaunchConfigurationBuilder implements
		LaunchConfigurationBuilder {

	protected IProject project;

	public void setProject(IProject project) {
		this.project = project;
	}

	public ILaunchConfiguration build() throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

}
