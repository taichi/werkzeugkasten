package werkzeugkasten.twowaysql.editor.conf;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

public class ColorManager implements IPropertyChangeListener {

	public static ColorManager getDefault() {
		return null;
	}

	public void initialize(IPreferenceStore pref) {

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

	}

	public void dispose() {
	}

}
