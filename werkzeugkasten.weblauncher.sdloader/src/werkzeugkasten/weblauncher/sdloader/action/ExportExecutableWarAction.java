package werkzeugkasten.weblauncher.sdloader.action;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;

import werkzeugkasten.common.action.EnablerAction;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.preferences.WebPreferences;
import werkzeugkasten.weblauncher.sdloader.job.ExecutableWarExportJob;

public class ExportExecutableWarAction extends EnablerAction {

	@Override
	protected boolean checkEnabled() {
		IProject project = Activator.findCurrentProject();
		return checkEnabled(project);
	}

	private static boolean checkEnabled(IProject project) {
		if (project != null
				&& ProjectUtil.hasNature(project, Constants.ID_NATURE)) {
			WebPreferences pref = Activator.getPreferences(project);
			return "SDLoader".equals(pref.getWebServerType());
		}
		return false;
	}

	public void run(IAction action) {
		IProject project = Activator.findCurrentProject();
		if (project == null) {
			return;
		}
		new ExecutableWarExportJob(project).schedule();
	}

}
