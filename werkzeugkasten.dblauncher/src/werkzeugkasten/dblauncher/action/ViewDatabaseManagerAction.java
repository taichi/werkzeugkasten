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
				open(new URL(buildManagerUrl(pref)), project, pref);
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

	private static void open(URL url, IProject project, DbPreferences pref)
			throws Exception {
		if (url != null) {
			IWorkbenchBrowserSupport support = PlatformUI.getWorkbench()
					.getBrowserSupport();
			IWebBrowser browser = null;
			if (support.isInternalWebBrowserAvailable()
					&& pref.useInternalWebBrowser()) {
				int flag = IWorkbenchBrowserSupport.AS_EDITOR
						| IWorkbenchBrowserSupport.LOCATION_BAR
						| IWorkbenchBrowserSupport.NAVIGATION_BAR
						| IWorkbenchBrowserSupport.STATUS
						| IWorkbenchBrowserSupport.PERSISTENT;
				browser = support.createBrowser(flag, Constants.ID_PLUGIN,
						null, null);
				Activator.entry(project, url);
			} else {
				browser = support.getExternalBrowser();
			}
			if (browser != null) {
				browser.openURL(url);
			}
		}
	}

}
