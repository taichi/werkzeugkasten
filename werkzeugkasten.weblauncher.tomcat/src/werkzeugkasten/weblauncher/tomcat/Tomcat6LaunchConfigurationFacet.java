package werkzeugkasten.weblauncher.tomcat;

import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.launch.WebLaunchConfigurationFacet;
import werkzeugkasten.weblauncher.tomcat.launch.TomcatLaunchConfigurationBuilder;

public class Tomcat6LaunchConfigurationFacet extends
		WebLaunchConfigurationFacet {

	public LaunchConfigurationBuilder getBuilder() {
		return new TomcatLaunchConfigurationBuilder();
	}

}
