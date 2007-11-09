package werkzeugkasten.dblauncher.preferences;

import static werkzeugkasten.dblauncher.Constants.*;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.h2.server.TcpServer;

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
	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences pref = new DefaultScope().getNode(ID_PLUGIN);
		pref.put(PREF_USE_INTERNAL_WEBBROWSER, String.valueOf(false));
		pref.put(PREF_DB_PORTNO, String.valueOf(TcpServer.DEFAULT_PORT));
		pref.put(PREF_WEB_PORTNO, String
				.valueOf(org.h2.engine.Constants.DEFAULT_HTTP_PORT));
		pref.putBoolean(PREF_IS_DEBUG, false);
		pref.put(PREF_USER, "sa");
	}

}
