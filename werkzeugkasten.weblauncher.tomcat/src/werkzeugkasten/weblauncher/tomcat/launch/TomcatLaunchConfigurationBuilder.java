package werkzeugkasten.weblauncher.tomcat.launch;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.catalina.startup.Bootstrap;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.debug.LaunchConfigurationFactory;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.preferences.WebPreferences;

public class TomcatLaunchConfigurationBuilder implements
		LaunchConfigurationBuilder {

	private IProject project;

	private String name;

	private String mainClass = Bootstrap.class.getName();

	private List<IRuntimeClasspathEntry> classpath = new ArrayList<IRuntimeClasspathEntry>();

	private List<IRuntimeClasspathEntry> srcpath = new ArrayList<IRuntimeClasspathEntry>();

	private String args;

	public void setProject(IProject project) {
		this.name = Constants.ID_PLUGIN + "." + project.getName();
		this.project = project;
		this.args = buildBootArgs(Activator.getPreferences(project));

	}

	protected String buildBootArgs(WebPreferences preferences) {
		return "start";
	}

	@SuppressWarnings("unchecked")
	protected void setUpClasspathEntries() {
		Bundle bundle = Activator.getDefault().getBundle();
		for (Enumeration e = bundle.getEntryPaths("/tomcat/bin"); e
				.hasMoreElements();) {
			String s = e.nextElement().toString().toLowerCase();
			if (s.endsWith(".jar")) {
				try {
					URL u = bundle.getEntry(s);
					URL local = FileLocator.toFileURL(u);
					String fullPath = new File(local.getPath())
							.getAbsolutePath();
					IPath path = Path.fromOSString(fullPath);
					classpath.add(JavaRuntime
							.newArchiveRuntimeClasspathEntry(path));
				} catch (IOException ex) {
					Activator.log(ex);
				}
			}
		}
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
						return getProject().getName()
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
		copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME,
				getProject().getName());

		WebPreferences pref = Activator.getPreferences(getProject());
		IPath baseDirPath = new Path(pref.getBaseDir());
		IWorkspaceRoot root = ProjectUtil.getWorkspaceRoot();
		IContainer c = root.getFolder(baseDirPath);
		String workDir = getProject().getLocation().toString();
		if (c.exists()) {
			workDir = baseDirPath.toString();
		}
		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_WORKING_DIRECTORY,
				workDir);

		copy
				.setAttribute(
						IJavaLaunchConfigurationConstants.ATTR_DEFAULT_CLASSPATH,
						false);
		copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH,
				getClasspath());

		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
				getMainClass());
		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS,
				getArgs());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public List<IRuntimeClasspathEntry> getClasspath() {
		return classpath;
	}

	public void setClasspath(List<IRuntimeClasspathEntry> classpath) {
		this.classpath = classpath;
	}

	public List<IRuntimeClasspathEntry> getSrcpath() {
		return srcpath;
	}

	public void setSrcpath(List<IRuntimeClasspathEntry> srcpath) {
		this.srcpath = srcpath;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public IProject getProject() {
		return project;
	}
}
