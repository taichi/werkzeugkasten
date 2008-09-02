package werkzeugkasten.weblauncher.launch;

import static werkzeugkasten.weblauncher.Constants.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.jdt.launching.JavaLaunchDelegate;

import werkzeugkasten.common.debug.LaunchUtil;
import werkzeugkasten.common.viewers.AbstractLightweightLabelDecorator;
import werkzeugkasten.weblauncher.Activator;

/**
 * @author taichi
 * 
 */
public class WebLaunchConfigurationDelegate extends JavaLaunchDelegate
		implements ILaunchConfigurationDelegate {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.
	 * eclipse.debug.core.ILaunchConfiguration, java.lang.String,
	 * org.eclipse.debug.core.ILaunch,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		IProject project = LaunchUtil.getProject(launch);
		super.launch(configuration, mode, launch, monitor);
		Activator.setLaunch(project, launch);
		AbstractLightweightLabelDecorator.updateDecorators(ID_DECORATOR,
				project);
	}
}
