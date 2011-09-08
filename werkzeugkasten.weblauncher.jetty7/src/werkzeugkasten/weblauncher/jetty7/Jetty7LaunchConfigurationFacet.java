package werkzeugkasten.weblauncher.jetty7;

import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.jetty7.launch.Jetty7LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.launch.WebLaunchConfigurationFacet;

/**
 * @author taichi
 */
public class Jetty7LaunchConfigurationFacet extends WebLaunchConfigurationFacet {

	public Jetty7LaunchConfigurationFacet() {
	}

	@Override
	public LaunchConfigurationBuilder getBuilder() {
		return new Jetty7LaunchConfigurationBuilder();
	}

}
