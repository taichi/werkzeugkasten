package werkzeugkasten.weblauncher.preferences;

import static werkzeugkasten.weblauncher.Constants.*;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

/**
 * @author taichi
 * 
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IEclipsePreferences pref = new DefaultScope().getNode(ID_PLUGIN);
		pref.put(PREF_WEB_PORTNO, "8080");
		pref.put(PREF_LIBRARY_TYPE, "Servlet 2.5 + JSP 2.1");
		pref.putBoolean(PREF_IS_DEBUG, true);
		pref.putBoolean(PREF_CHECK_SERVER, false);
		pref.putBoolean(PREF_USE_INTERNAL_WEBBROWSER, false);
		pref.put(PREF_EXPORT_IGNORE, "^(\\..*|.*\\.(bak|tmp|ori?g))$");

	}

}
