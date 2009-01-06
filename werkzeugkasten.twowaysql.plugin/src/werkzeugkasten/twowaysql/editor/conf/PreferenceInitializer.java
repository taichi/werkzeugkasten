package werkzeugkasten.twowaysql.editor.conf;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.Constants.COLORING;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public PreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences pref = new DefaultScope()
				.getNode(Constants.ID_PLUGIN);

		setUpDefaultColorSettings(pref);
	}

	protected void setUpDefaultColorSettings(IEclipsePreferences pref) {
		pref.put(COLORING.KEYWORD.getPrefColorKey(), StringConverter
				.asString(new RGB(127, 0, 85))); // Mazenda
		pref.putInt(COLORING.KEYWORD.getPrefStyleKey(), SWT.BOLD);

		pref.put(COLORING.EXPRESSION.getPrefColorKey(), StringConverter
				.asString(new RGB(100, 70, 50))); // Chacoal
		pref.putInt(COLORING.EXPRESSION.getPrefStyleKey(), SWT.BOLD);

		pref.put(COLORING.BIND_TYPE.getPrefColorKey(), StringConverter
				.asString(new RGB(0, 0, 254)));
		pref.putInt(COLORING.BIND_TYPE.getPrefStyleKey(), SWT.NONE);

		pref.put(COLORING.COMMENT.getPrefColorKey(), StringConverter
				.asString(new RGB(63, 127, 95))); // Green
		pref.putInt(COLORING.COMMENT.getPrefStyleKey(), SWT.NONE);

		pref.put(COLORING.SKIP.getPrefColorKey(), StringConverter
				.asString(new RGB(100, 100, 100))); // Gray
		pref.putInt(COLORING.SKIP.getPrefStyleKey(), SWT.ITALIC);

		pref.put(COLORING.MAYBE_SKIP.getPrefColorKey(), StringConverter
				.asString(new RGB(100, 100, 100)));
		pref.putInt(COLORING.MAYBE_SKIP.getPrefStyleKey(), SWT.NONE);

		pref.put(COLORING.TXT.getPrefColorKey(), StringConverter
				.asString(PreferenceConverter.COLOR_DEFAULT_DEFAULT));
		pref.putInt(COLORING.TXT.getPrefStyleKey(), SWT.NONE);
	}

}
