package werkzeugkasten.dblauncher.launch;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.jdt.launching.JavaLaunchDelegate;

import werkzeugkasten.common.debug.LaunchUtil;
import werkzeugkasten.common.viewers.AbstractLightweightLabelDecorator;
import werkzeugkasten.dblauncher.Activator;
import werkzeugkasten.dblauncher.Constants;

/**
 * @author taichi
 * 
 */
public class DbLaunchConfigurationDelegate extends JavaLaunchDelegate implements
		ILaunchConfigurationDelegate {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.launching.JavaLaunchDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration,
	 *      java.lang.String, org.eclipse.debug.core.ILaunch,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		super.launch(configuration, mode, launch, monitor);
		IProject project = LaunchUtil.getProject(launch);
		if (project != null && launch.isTerminated() == false) {
			Activator.setLaunch(project, launch);
			AbstractLightweightLabelDecorator.updateDecorators(
					Constants.ID_DECORATOR, project);
		}
	}
}
