package werkzeugkasten.weblauncher.action;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.ui.jarpackager.JarPackageData;
import org.eclipse.jface.action.IAction;

import werkzeugkasten.common.action.EnablerAction;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.job.WarExportJob;
import werkzeugkasten.weblauncher.preferences.WebPreferences;

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

		WebPreferences pref = Activator.getPreferences(project);
		JarPackageData data = setUp(project, pref);
		IPath basePath = new Path(pref.getBaseDir());
		new WarExportJob(data, basePath).schedule();
	}

	protected JarPackageData setUp(IProject project, WebPreferences pref) {
		JarPackageData data = new JarPackageData();
		data.setCompress(false);
		data.setBuildIfNeeded(true);
		data.setOverwrite(true);

		IPath distPath = project.getLocation();
		String ctx = new Path(pref.getContextName()).removeTrailingSeparator()
				.toOSString();
		if (StringUtil.isEmpty(ctx)) {
			distPath = distPath.append(project.getName());
		} else {
			distPath = distPath.append(ctx);
		}
		distPath = distPath.addFileExtension("war");
		data.setJarLocation(distPath);
		return data;
	}
}
