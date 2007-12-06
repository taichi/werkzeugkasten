package werkzeugkasten.weblauncher.tomcat55;

import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.launch.WebLaunchConfigurationFacet;
import werkzeugkasten.weblauncher.tomcat55.launch.TomcatLaunchConfigurationBuilder;

public class Tomcat55LaunchConfigurationFacet extends
		WebLaunchConfigurationFacet {

	public LaunchConfigurationBuilder getBuilder() {
		return new TomcatLaunchConfigurationBuilder();
	}

}
