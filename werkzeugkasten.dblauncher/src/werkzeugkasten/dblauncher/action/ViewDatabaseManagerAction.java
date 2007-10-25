package werkzeugkasten.dblauncher.action;

import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

import werkzeugkasten.common.action.EnablerAction;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.dblauncher.Activator;
import werkzeugkasten.dblauncher.Constants;
import werkzeugkasten.dblauncher.preferences.DbPreferences;

/**
 * @author taichi
 * 
 */
public class ViewDatabaseManagerAction extends EnablerAction {

	/**
	 * 
	 */
	public ViewDatabaseManagerAction() {
		super();
	}

	@Override
	protected boolean checkEnabled() {
		IProject project = ToggleServerAction.findCurrentProject();
		if (project != null
				&& ProjectUtil.hasNature(project, Constants.ID_NATURE)) {
			ILaunch l = Activator.getLaunch(project);
			return l != null && l.isTerminated() == false;
		}
		return false;
	}

	public void run(IAction action) {
		try {
			IProject project = ToggleServerAction.findCurrentProject();
			DbPreferences pref = Activator.getPreferences(project);
			if (pref != null) {
				IWorkbenchBrowserSupport support = PlatformUI.getWorkbench()
						.getBrowserSupport();
				IWebBrowser browser = support.getExternalBrowser();
				browser.openURL(new URL(buildManagerUrl(pref)));
			}
		} catch (Exception e) {
			Activator.log(e);
		}
	}

	private String buildManagerUrl(DbPreferences pref) {
		StringBuffer stb = new StringBuffer();
		stb.append("http://localhost:");
		stb.append(pref.getWebPortNo());
		return stb.toString();
	}

}
