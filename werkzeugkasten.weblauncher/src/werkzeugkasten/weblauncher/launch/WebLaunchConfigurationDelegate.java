package werkzeugkasten.weblauncher.launch;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.ISourceLocator;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourceLookupDirector;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaLaunchDelegate;
import org.eclipse.jdt.launching.JavaRuntime;

import werkzeugkasten.common.debug.LaunchUtil;

/**
 * @author taichi
 * 
 */
public class WebLaunchConfigurationDelegate extends JavaLaunchDelegate
		implements ILaunchConfigurationDelegate {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration,
	 *      java.lang.String, org.eclipse.debug.core.ILaunch,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		super.setDefaultSourceLocator(launch, configuration);
		IProject project = LaunchUtil.getProject(launch);
		ISourceLocator locator = launch.getSourceLocator();
		if (locator instanceof ISourceLookupDirector) {
			ISourceLookupDirector director = (ISourceLookupDirector) locator;
			if (project != null) {
				IJavaProject javap = JavaCore.create(project);
				IRuntimeClasspathEntry[] entries = JavaRuntime
						.computeUnresolvedRuntimeClasspath(javap);
				IRuntimeClasspathEntry[] resolved = JavaRuntime
						.resolveSourceLookupPath(entries, configuration);
				ISourceContainer[] sourceContainers = JavaRuntime
						.getSourceContainers(resolved);
				director.setSourceContainers(sourceContainers);
			}
		}
		super.launch(configuration, mode, launch, monitor);
	}
}
