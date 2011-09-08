package werkzeugkasten.weblauncher.jetty8;

import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.jetty8.launch.Jetty8LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.launch.WebLaunchConfigurationFacet;

/**
 * @author taichi
 */
public class Jetty8LaunchConfigurationFacet extends WebLaunchConfigurationFacet {

	public Jetty8LaunchConfigurationFacet() {
	}

	@Override
	public LaunchConfigurationBuilder getBuilder() {
		return new Jetty8LaunchConfigurationBuilder();
	}
}
