package werkzeugkasten.weblauncher.preferences.impl;

import static werkzeugkasten.weblauncher.Constants.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.preferences.WebPreferences;

public class WebPreferencesImpl implements WebPreferences {

	protected ScopedPreferenceStore store;

	public WebPreferencesImpl(IProject project) {
		store = new ScopedPreferenceStore(new ProjectScope(project), ID_PLUGIN);
		setupPreferences(project, store);
	}

	public static void setupPreferences(IProject project, IPreferenceStore store) {
		String baseDir = store.getString(PREF_BASE_DIR);
		if (StringUtil.isEmpty(baseDir)) {
			store.setValue(PREF_BASE_DIR, getDefaultBaseDir(project));
		}
	}

	public static String getDefaultBaseDir(IProject project) {
		return getResoucePath(project, new String[] { "src/main/webapp",
				"webapp", "webapps", "WebContent", "WebContents", "/" });
	}

	public static String getResoucePath(IProject project, String[] maybepath) {
		String result = "";
		try {
			if (project != null) {
				for (int i = 0; i < maybepath.length; i++) {
					IResource r = project.findMember(maybepath[i]);
					if (r != null && r.exists()) {
						result = r.getFullPath().toString();
						break;
					}
				}
			}
		} catch (Exception e) {
			Activator.log(e);
		}
		return result;
	}

	public boolean checkServerWhenOpen() {
		return store.getBoolean(PREF_CHECK_SERVER);
	}

	public String getBaseDir() {
		return store.getString(PREF_BASE_DIR);
	}

	public String getConfig() {
		return store.getString(PREF_CONFIG);
	}

	public String getContextName() {
		return store.getString(PREF_CONTEXT_NAME);
	}

	public String getWebPortNo() {
		return store.getString(PREF_WEB_PORTNO);
	}

	public boolean isDebug() {
		return store.getBoolean(PREF_IS_DEBUG);
	}

	public void setBaseDir(String path) {
		store.setValue(PREF_BASE_DIR, path);
	}

	public void setCheckServerWhenOpen(boolean is) {
		store.setValue(PREF_CHECK_SERVER, is);
	}

	public void setConfig(String path) {
		store.setValue(PREF_CONFIG, path);
	}

	public void setContextName(String name) {
		store.setValue(PREF_CONTEXT_NAME, name);
	}

	public void setDebug(boolean is) {
		store.setValue(PREF_IS_DEBUG, is);
	}

	public void setInternalWebBrowser(boolean is) {
		store.setValue(PREF_USE_INTERNAL_WEBBROWSER, is);
	}

	public void setWebPortNo(String no) {
		store.setValue(PREF_WEB_PORTNO, no);
	}

	public boolean useInternalWebBrowser() {
		return store.getBoolean(PREF_USE_INTERNAL_WEBBROWSER);
	}

	public String getWebServerType() {
		return store.getString(PREF_WEB_SERVER_TYPE);
	}

	public void setWebServerType(String type) {
		store.setValue(PREF_WEB_SERVER_TYPE, type);
	}

}
