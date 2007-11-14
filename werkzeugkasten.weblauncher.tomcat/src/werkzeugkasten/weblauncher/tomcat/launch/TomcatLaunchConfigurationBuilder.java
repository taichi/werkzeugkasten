package werkzeugkasten.weblauncher.tomcat.launch;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.catalina.startup.Bootstrap;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
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
import org.osgi.framework.Bundle;

import werkzeugkasten.common.debug.LaunchConfigurationFactory;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.preferences.WebPreferences;

public class TomcatLaunchConfigurationBuilder implements
		LaunchConfigurationBuilder {

	protected static final String CONTEXT_XML = ".settings/"
			+ Constants.ID_PLUGIN + ".tomcat.context.xml";
	private IProject project;

	private String name;

	private String mainClass = Bootstrap.class.getName();

	private List<IRuntimeClasspathEntry> classpath = new ArrayList<IRuntimeClasspathEntry>();

	private List<IRuntimeClasspathEntry> srcpath = new ArrayList<IRuntimeClasspathEntry>();

	private String args;

	public void setProject(IProject project) {
		this.name = Constants.ID_PLUGIN + "." + project.getName();
		this.project = project;
		this.args = buildBootArgs(project, Activator.getPreferences(project));
		setUpClasspathEntries();
	}

	protected String buildBootArgs(IProject project, WebPreferences preferences) {
		Bundle bundle = werkzeugkasten.weblauncher.tomcat.Activator
				.getDefault().getBundle();
		URL u = bundle.getEntry("/tomcat");
		try {
			u = FileLocator.toFileURL(u);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		String tomcatbase = new File(u.getPath()).getAbsolutePath();

		StringBuilder stb = new StringBuilder();

		stb
				.append(" -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager");

		stb.append(" -Djava.util.logging.config.file=\"");
		stb.append(tomcatbase);
		stb.append("/conf/logging.properties\"");

		stb.append(" -Djava.endorsed.dirs=\"");
		stb.append(tomcatbase);
		stb.append("/endorsed\"");

		stb.append(" -Dcatalina.base=\"");
		stb.append(tomcatbase);
		stb.append("\"");
		stb.append(" -Dcatalina.home=\"");
		stb.append(tomcatbase);
		stb.append("\"");

		stb.append(" -Ddoc_base=\"");
		IPath p = getProject().getLocation().removeLastSegments(1).append(
				preferences.getBaseDir());
		stb.append(p.toOSString());
		stb.append("\"");

		stb.append(" -Ddblauncher.ctx.loc=\"");
		stb.append(getProject().getLocation().append(CONTEXT_XML).toOSString());
		stb.append("\"");
		stb.append(" -Ddblauncher.ctx.name=");
		stb.append(preferences.getContextName());
		return stb.toString();
	}

	@SuppressWarnings("unchecked")
	protected void setUpClasspathEntries() {
		Bundle bundle = werkzeugkasten.weblauncher.tomcat.Activator
				.getDefault().getBundle();
		for (Enumeration e = bundle.getEntryPaths("/tomcat/bin/"); e
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
		IFile f = getProject().getFile(CONTEXT_XML);
		if (f == null || f.exists() == false) {
			buildXML(f);
		}

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

	private void buildXML(IFile f) {
		StringBuilder stb = new StringBuilder();
		stb.append("<Context docBase=\"${doc_base}\" className=\"");
		stb
				.append("werkzeugkasten.weblauncher.tomcat.startup.WebLauncherContext");
		stb.append("\"/>");
		InputStream in = null;
		try {
			byte[] bytes = stb.toString().getBytes("UTF-8");
			in = new ByteArrayInputStream(bytes);
			f.create(in, IResource.FORCE, null);
			f.getParent().refreshLocal(IResource.DEPTH_ONE, null);
		} catch (Exception e) {
			Activator.log(e);
		} finally {
			StreamUtil.close(in);
		}
	}

	protected void setUp(ILaunchConfigurationWorkingCopy copy)
			throws CoreException {
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
				toMemento(getClasspath()));

		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
				getMainClass());
		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS,
				"start");
		copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
				getArgs());
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
