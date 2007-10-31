package werkzeugkasten.dblauncher.launch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.h2.tools.Server;

import werkzeugkasten.common.debug.LaunchConfigurationFactory;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.dblauncher.Activator;
import werkzeugkasten.dblauncher.Constants;
import werkzeugkasten.dblauncher.preferences.DbPreferences;
import werkzeugkasten.dblauncher.variable.Variable;

/**
 * @author taichi
 */
public class DbConfigurationBuilder {

	private IProject project;

	private String name;

	private String mainClass = Server.class.getName();

	private IRuntimeClasspathEntry[] classpath;

	private IRuntimeClasspathEntry[] srcpath;

	private String args;

	public DbConfigurationBuilder(IProject project) {
		this(Constants.ID_PLUGIN + "." + project.getName(), project);
	}

	public DbConfigurationBuilder(String name, IProject project) {
		super();
		this.name = name;
		this.project = project;
		this.classpath = new IRuntimeClasspathEntry[] { JavaRuntime
				.newVariableRuntimeClasspathEntry(Variable.LIB) };
		this.srcpath = new IRuntimeClasspathEntry[] { JavaRuntime
				.newVariableRuntimeClasspathEntry(Variable.SRC) };
	}

	public ILaunchConfiguration build() throws CoreException {
		return LaunchConfigurationFactory
				.create(new LaunchConfigurationFactory.CreationHandler() {
					public String getTypeName() {
						return Constants.ID_H2_LAUNCH_CONFIG;
					}

					public void setUp(ILaunchConfigurationWorkingCopy copy)
							throws CoreException {
						DbConfigurationBuilder.this.setUp(copy);
					};

					public String getConfigName() {
						return Constants.ID_PLUGIN + "." + project.getName();
					}

					public boolean equals(ILaunchConfiguration config)
							throws CoreException {
						return true;
					}
				});
	}

	public void setUp(ILaunchConfigurationWorkingCopy copy)
			throws CoreException {
		copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME,
				project.getName());

		DbPreferences pref = Activator.getPreferences(project);
		IPath baseDirPath = new Path(pref.getBaseDir());
		IWorkspaceRoot root = ProjectUtil.getWorkspaceRoot();
		IContainer c = root.getFolder(baseDirPath);
		String workDir = project.getLocation().toString();
		if (c.exists()) {
			workDir = baseDirPath.toString();
		}
		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_WORKING_DIRECTORY,
				workDir);

		if (hasMain(getProject(), getMainClass()) == false) {
			copy.setAttribute(
					IJavaLaunchConfigurationConstants.ATTR_DEFAULT_CLASSPATH,
					false);
			copy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH,
					toMemento(getClasspath()));
			if (srcpath != null && 0 < srcpath.length) {
				copy
						.setAttribute(
								IJavaLaunchConfigurationConstants.ATTR_DEFAULT_SOURCE_PATH,
								false);
				copy.setAttribute(
						IJavaLaunchConfigurationConstants.ATTR_SOURCE_PATH,
						toMemento(getSrcpath()));
			}
		}

		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
				getMainClass());
		copy.setAttribute(
				IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS,
				getArgs());
	}

	private List<String> toMemento(IRuntimeClasspathEntry[] classpath)
			throws CoreException {
		List<String> classpathList = new ArrayList<String>(classpath.length);
		for (int i = 0; i < classpath.length; i++) {
			classpathList.add(classpath[i].getMemento());
		}
		return classpathList;
	}

	public static boolean hasMain(IProject project, String mainClass) {
		boolean result = false;
		try {
			IJavaProject jp = JavaCore.create(project);
			IType type = jp.findType(mainClass);
			result = type != null && type.exists();
		} catch (JavaModelException e) {
			Activator.log(e);
		}
		return result;
	}

	/**
	 * @return Returns the args.
	 */
	public String getArgs() {
		return this.args;
	}

	/**
	 * @param args
	 *            The args to set.
	 */
	public void setArgs(String args) {
		this.args = args;
	}

	/**
	 * @return Returns the classpath.
	 */
	public IRuntimeClasspathEntry[] getClasspath() {
		return this.classpath;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return Returns the project.
	 */
	public IProject getProject() {
		return this.project;
	}

	/**
	 * @return Returns the srcpath.
	 */
	public IRuntimeClasspathEntry[] getSrcpath() {
		return this.srcpath;
	}

	/**
	 * @param srcpath
	 *            The srcpath to set.
	 */
	public void setSrcpath(IRuntimeClasspathEntry[] srcpath) {
		this.srcpath = srcpath;
	}

	/**
	 * @return Returns the mainClass.
	 */
	public String getMainClass() {
		return this.mainClass;
	}

	/**
	 * @param mainClass
	 *            The mainClass to set.
	 */
	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
}
