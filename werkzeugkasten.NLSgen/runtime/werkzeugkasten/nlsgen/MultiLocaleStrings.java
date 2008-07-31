package werkzeugkasten.nlsgen;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MultiLocaleStrings {

	protected Map<String, ResourceBundle> bundles = new HashMap<String, ResourceBundle>();

	protected MultiLocaleStrings() {
	}

	public void add(ResourceBundle bundle) {
		this.bundles.put(bundle.getLocale().getLanguage(), bundle);
	}

	protected String getMessage(Locale locale, String key, Object... objects) {
		ResourceBundle rb = this.bundles.get(locale.getLanguage());
		if (rb == null) {
			rb = this.bundles.get("");
			if (rb == null) {
				return null;
			}
		}
		String s = rb.getString(key);
		if (objects != null && 0 < objects.length) {
			return String.format(s, objects);
		}
		return s;
	}
}
