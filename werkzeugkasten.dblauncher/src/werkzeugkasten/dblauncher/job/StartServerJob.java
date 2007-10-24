package werkzeugkasten.dblauncher.job;

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
import werkzeugkasten.dblauncher.Activator;
import werkzeugkasten.dblauncher.Constants;
import werkzeugkasten.dblauncher.launch.DbConfigurationBuilder;
import werkzeugkasten.dblauncher.nls.Strings;
import werkzeugkasten.dblauncher.preferences.DbPreferences;

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
	public boolean belongsTo(Object family) {
		return FAMILY_START_SERVER_JOB == family;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		ILaunch launch = Activator.getLaunch(project);
		if (project != null
				&& project.getSessionProperty(Constants.KEY_JOB_PROCESSING) == null
				&& (launch == null || launch.isTerminated())) {
			try {
				project.setSessionProperty(Constants.KEY_JOB_PROCESSING, "");
				ILaunchConfiguration config = new DbConfigurationBuilder(
						project).build();
				DbPreferences pref = Activator.getPreferences(project);
				config.launch(pref.isDebug() ? ILaunchManager.DEBUG_MODE
						: ILaunchManager.RUN_MODE, monitor);
				AbstractLightweightLabelDecorator.updateDecorators(
						Constants.ID_DECORATOR, project);
			} finally {
				project.setSessionProperty(Constants.KEY_JOB_PROCESSING, null);
			}
		}
		return Status.OK_STATUS;
	}
}
