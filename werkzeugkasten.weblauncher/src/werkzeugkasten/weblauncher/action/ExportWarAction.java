package werkzeugkasten.weblauncher.action;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;

import werkzeugkasten.common.action.EnablerAction;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.job.WarExportJob;

public class ExportWarAction extends EnablerAction {

	protected boolean checkEnabled() {
		IProject project = Activator.findCurrentProject();
		return checkEnabled(project);
	}

	private static boolean checkEnabled(IProject project) {
		return project != null
				&& ProjectUtil.hasNature(project, Constants.ID_NATURE);
	}

	public void run(IAction action) {
		IProject project = Activator.findCurrentProject();
		if (project == null) {
			return;
		}
		new WarExportJob(project).schedule();
	}

}
