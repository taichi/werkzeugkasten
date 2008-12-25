package werkzeugkasten.twowaysql.editor.conf;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import werkzeugkasten.twowaysql.Constants;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public PreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences pref = new DefaultScope()
				.getNode(Constants.ID_PLUGIN);
		// TODO setup default color setting
	}

}
