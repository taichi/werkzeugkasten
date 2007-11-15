package werkzeugkasten.weblauncher.sdloader;

import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.launch.WebLaunchConfigurationFacet;
import werkzeugkasten.weblauncher.sdloader.launch.SDLoaderLaunchConfigurationBuilder;

public class SDLoaderLaunchConfigurationFacet extends
		WebLaunchConfigurationFacet {

	public LaunchConfigurationBuilder getBuilder() {
		return new SDLoaderLaunchConfigurationBuilder();
	}

}
