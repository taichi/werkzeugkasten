package werkzeugkasten.dblauncher.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.h2.server.TcpServer;

import werkzeugkasten.dblauncher.Constants;

/**
 * @author taichi
 * 
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * 
	 */
	public PreferenceInitializer() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IEclipsePreferences pref = new DefaultScope()
				.getNode(Constants.ID_PLUGIN);
		pref.put(Constants.PREF_USE_INTERNAL_WEBBROWSER, String.valueOf(false));
		pref.put(Constants.PREF_DB_PORTNO, String
				.valueOf(TcpServer.DEFAULT_PORT));
		pref.put(Constants.PREF_WEB_PORTNO, String
				.valueOf(org.h2.engine.Constants.DEFAULT_HTTP_PORT));
		pref.putBoolean(Constants.PREF_IS_DEBUG, false);
		pref.put(Constants.PREF_USER, "sa");
	}

}
