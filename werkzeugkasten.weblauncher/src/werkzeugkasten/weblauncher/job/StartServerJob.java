package werkzeugkasten.weblauncher.job;

import static werkzeugkasten.weblauncher.Constants.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;

import werkzeugkasten.common.viewers.AbstractLightweightLabelDecorator;
import werkzeugkasten.launcher.LaunchConfigurationBuilder;
import werkzeugkasten.launcher.LaunchConfigurationFacet;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.nls.Strings;
import werkzeugkasten.weblauncher.preferences.WebPreferences;

public class StartServerJob extends WorkspaceJob {
	private static final Object FAMILY_START_SERVER_JOB = new Object();

	protected IProject project;

	public StartServerJob(IProject project) {
		super(Strings.MSG_START_SERVER);
		this.project = project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
	 */
	@Override
	public boolean belongsTo(Object family) {
		return FAMILY_START_SERVER_JOB == family;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		ILaunch launch = Activator.getLaunch(project);
		if (project != null
				&& project.getSessionProperty(KEY_JOB_PROCESSING) == null
				&& (launch == null || launch.isTerminated())) {
			try {
				project.setSessionProperty(KEY_JOB_PROCESSING, "");
				WebPreferences pref = Activator.getPreferences(project);
				LaunchConfigurationFacet facet = Activator.getFacetRegistry()
						.find(pref.getWebServerType());
				if (facet != null) {
					LaunchConfigurationBuilder builder = facet.getBuilder();
					builder.setProject(project);
					ILaunchConfiguration config = builder.build();
					launch = config.launch(
							pref.isDebug() ? ILaunchManager.DEBUG_MODE
									: ILaunchManager.RUN_MODE, monitor);
					Activator.setLaunch(project, launch);
					AbstractLightweightLabelDecorator.updateDecorators(
							ID_DECORATOR, project);
				}
			} catch (Exception e) {
				Activator.log(e);
			} finally {
				project.setSessionProperty(KEY_JOB_PROCESSING, null);
			}
		}
		return Status.OK_STATUS;
	}

}
