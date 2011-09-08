package werkzeugkasten.weblauncher.jetty7.launch;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
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
import org.eclipse.jetty.start.Main;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.debug.LaunchConfigurationFactory;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.resource.ResourceUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.preferences.WebPreferences;

public class Jetty7LaunchConfigurationBuilder implements
		LaunchConfigurationBuilder {

	private IProject project;

	private String name;

	private String mainClass = Main.class.getName();

	private List<IRuntimeClasspathEntry> classpath = new ArrayList<IRuntimeClasspathEntry>();

	private List<IRuntimeClasspathEntry> srcpath = new ArrayList<IRuntimeClasspathEntry>();

	private String args;

	@Override
	public void setProject(IProject project) {
		this.name = Constants.ID_PLUGIN + "." + project.getName();
		this.project = project;
		this.args = buildBootArgs(project, Activator.getPreferences(project));
		setUpClasspathEntries();
	}

	protected String buildBootArgs(IProject project, WebPreferences preferences) {
		Bundle bundle = werkzeugkasten.weblauncher.jetty7.Activator
				.getDefault().getBundle();
		URL u = bundle.getEntry("/jetty");
		try {
			u = FileLocator.toFileURL(u);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		String jettybase = new File(u.getPath()).getAbsolutePath();

		StringBuilder stb = new StringBuilder();

		stb.append(" -Djetty.home=");
		stb.append(ResourceUtil.quote(jettybase));

		stb.append(" -Ddoc_base=");
		IPath p = getProject().getLocation().removeLastSegments(1)
				.append(preferences.getBaseDir());
		stb.append(ResourceUtil.quote(p.toString()));
		stb.append(" -Dctx=");
		stb.append(preferences.getContextName());

		String port = preferences.getWebPortNo();
		if (StringUtil.isEmpty(port) == false && port.matches("\\d*")) {
			stb.append(" -Djetty.port=");
			stb.append(port);
		}
		return stb.toString();
	}

	protected void setUpClasspathEntries() {
		Bundle bundle = werkzeugkasten.weblauncher.jetty7.Activator
				.getDefault().getBundle();
		try {
			URL u = bundle.getEntry("/jetty/start.jar");
			URL local = FileLocator.toFileURL(u);
			String fullPath = new File(local.getPath()).getAbsolutePath();
			IPath path = Path.fromOSString(fullPath);
			this.classpath.add(JavaRuntime
					.newArchiveRuntimeClasspathEntry(path));
		} catch (IOException ex) {
			Activator.log(ex);
		}
	}

	@Override
	public ILaunchConfiguration build() throws CoreException {
		return LaunchConfigurationFactory
				.create(new LaunchConfigurationFactory.CreationHandler() {
					@Override
					public String getTypeName() {
						return Constants.ID_LAUNCH_CONFIG;
					}

					@Override
					public void setUp(ILaunchConfigurationWorkingCopy copy)
							throws CoreException {
						Jetty7LaunchConfigurationBuilder.this.setUp(copy);
					};

					@Override
					public String getConfigName() {
						return getProject().getName()
								+ "."
								+ werkzeugkasten.weblauncher.jetty7.Activator.PLUGIN_ID;
					}

					@Override
					public boolean equals(ILaunchConfiguration config)
							throws CoreException {
						return Activator.isSameVersion(config);
					}
				});
	}

	protected void setUp(ILaunchConfigurationWorkingCopy copy)
			throws CoreException {
		Activator.setVersion(copy);
		copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME,
				getProject().getName());

		WebPreferences pref = Activator.getPreferences(getProject());
		IPath baseDirPath = new Path(pref.getBaseDir());
		IWorkspaceRoot root = ProjectUtil.getWorkspaceRoot();
		IResource r = root.findMember(baseDirPath);
		String workDir = getProject().getLocation().toString();
		if (r != null && r.exists()) {
			workDir = baseDirPath.toString();
		}
		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_WORKING_DIRECTORY,
				workDir);

		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_DEFAULT_CLASSPATH, false);
		copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH,
				toMemento(getClasspath()));

		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
				getMainClass());
		copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
				getArgs());

		Activator.setSourceLocator(this.project, copy);
	}

	private List<String> toMemento(List<IRuntimeClasspathEntry> classpath)
			throws CoreException {
		List<String> classpathList = new ArrayList<String>(classpath.size());
		for (IRuntimeClasspathEntry e : classpath) {
			classpathList.add(e.getMemento());
		}
		return classpathList;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMainClass() {
		return this.mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public List<IRuntimeClasspathEntry> getClasspath() {
		return this.classpath;
	}

	public void setClasspath(List<IRuntimeClasspathEntry> classpath) {
		this.classpath = classpath;
	}

	public List<IRuntimeClasspathEntry> getSrcpath() {
		return this.srcpath;
	}

	public void setSrcpath(List<IRuntimeClasspathEntry> srcpath) {
		this.srcpath = srcpath;
	}

	public String getArgs() {
		return this.args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public IProject getProject() {
		return this.project;
	}
}
