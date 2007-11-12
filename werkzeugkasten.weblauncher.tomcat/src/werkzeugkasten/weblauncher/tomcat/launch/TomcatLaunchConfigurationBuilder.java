package werkzeugkasten.weblauncher.tomcat.launch;

import org.apache.catalina.startup.Bootstrap;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;

import werkzeugkasten.common.debug.LaunchConfigurationFactory;
import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.preferences.WebPreferences;

public class TomcatLaunchConfigurationBuilder implements
		LaunchConfigurationBuilder {

	private IProject project;

	private String name;

	private String mainClass = Bootstrap.class.getName();

	private IRuntimeClasspathEntry[] classpath;

	private IRuntimeClasspathEntry[] srcpath;

	private String args;

	public void setProject(IProject project) {
		this.name = Constants.ID_PLUGIN + "." + project.getName();
		this.project = project;
		this.args = buildBootArgs(Activator.getPreferences(project));
	}

	protected String buildBootArgs(WebPreferences preferences) {
		return "start";
	}

	public ILaunchConfiguration build() throws CoreException {
		return LaunchConfigurationFactory
				.create(new LaunchConfigurationFactory.CreationHandler() {
					public String getTypeName() {
						return Constants.ID_LAUNCH_CONFIG;
					}

					public void setUp(ILaunchConfigurationWorkingCopy copy)
							throws CoreException {
						TomcatLaunchConfigurationBuilder.this.setUp(copy);
					};

					public String getConfigName() {
						return project.getName()
								+ "."
								+ werkzeugkasten.weblauncher.tomcat.Constants.ID_PLUGIN;
					}

					public boolean equals(ILaunchConfiguration config)
							throws CoreException {
						return true;
					}
				});
	}

	protected void setUp(ILaunchConfigurationWorkingCopy copy) {
		String a = name + classpath + srcpath + args + mainClass;
		System.out.println(a);
	}
}
