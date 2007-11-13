package werkzeugkasten.dblauncher.launch;

import static werkzeugkasten.dblauncher.Constants.*;

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

/**
 * @author taichi
 * 
 */
public class DbLaunchConfigurationDelegate extends JavaLaunchDelegate implements
		ILaunchConfigurationDelegate {
	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		super.launch(configuration, mode, launch, monitor);
		IProject project = LaunchUtil.getProject(launch);
		Activator.setLaunch(project, launch);
		AbstractLightweightLabelDecorator.updateDecorators(ID_DECORATOR,
				project);

	}
}
