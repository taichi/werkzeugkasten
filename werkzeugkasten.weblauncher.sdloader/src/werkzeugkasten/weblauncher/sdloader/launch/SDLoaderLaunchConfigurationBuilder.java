package werkzeugkasten.weblauncher.sdloader.launch;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
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
import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.preferences.WebPreferences;
import werkzeugkasten.weblauncher.sdloader.Main;

public class SDLoaderLaunchConfigurationBuilder implements
		LaunchConfigurationBuilder {

	protected static final String CONTEXT_XML = ".settings/"
			+ Constants.ID_PLUGIN + ".sdloader.context.xml";
	private IProject project;

	private String name;

	private String mainClass = Main.class.getName();

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
		StringBuilder stb = new StringBuilder();

		stb.append("-Dweblauncher.port=");
		stb.append(preferences.getWebPortNo());
		stb.append(" -Dweblauncher.ctx.loc=\"");
		stb.append(getProject().getLocation().append(CONTEXT_XML).toString()+"\"");
		stb.append(" -Dweblauncher.ctx.doc_base=\"");
		IPath docBase = getProject().getLocation().removeLastSegments(1)
				.append(preferences.getBaseDir());
		stb.append(docBase.toString()+"\"");
		return stb.toString();
	}

	@SuppressWarnings("unchecked")
	protected void setUpClasspathEntries() {
		Bundle bundle = werkzeugkasten.weblauncher.sdloader.Activator
				.getDefault().getBundle();
		for (Enumeration e = bundle.getEntryPaths("/sdloader/lib/"); e
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
						SDLoaderLaunchConfigurationBuilder.this.setUp(copy);
					};

					public String getConfigName() {
						return getProject().getName()
								+ "."
								+ werkzeugkasten.weblauncher.sdloader.Constants.ID_PLUGIN;
					}

					public boolean equals(ILaunchConfiguration config)
							throws CoreException {
						return true;
					}
				});
	}

	private void buildXML(IFile f) {
		WebPreferences preferences = Activator.getPreferences(getProject());

		StringBuilder stb = new StringBuilder();
		stb.append("<Context ");
		stb.append(" path=\"");
		stb.append(preferences.getContextName());
		stb.append("\" docBase=\"${weblauncher.ctx.doc_base}\" />");

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

		copy
				.setAttribute(
						IJavaLaunchConfigurationConstants.ATTR_DEFAULT_CLASSPATH,
						false);
		copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH,
				toMemento(getClasspath()));

		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
				getMainClass());
		copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
				getArgs());
		Activator.setSourceLocator(project, copy);
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
