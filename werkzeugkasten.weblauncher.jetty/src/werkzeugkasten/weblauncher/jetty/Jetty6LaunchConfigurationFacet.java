package werkzeugkasten.weblauncher.jetty;

import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.jetty.launch.Jetty6LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.launch.WebLaunchConfigurationFacet;

public class Jetty6LaunchConfigurationFacet extends WebLaunchConfigurationFacet {

	public LaunchConfigurationBuilder getBuilder() {
		return new Jetty6LaunchConfigurationBuilder();
	}

}
