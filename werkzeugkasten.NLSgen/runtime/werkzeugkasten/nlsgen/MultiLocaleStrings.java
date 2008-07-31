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

	protected String getMessage(Locale locale, String key) {
		ResourceBundle rb = this.bundles.get(locale.getLanguage());
		return rb != null ? rb.getString(key) : null;
	}
}
