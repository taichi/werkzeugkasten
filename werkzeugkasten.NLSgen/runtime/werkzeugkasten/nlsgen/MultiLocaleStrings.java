package werkzeugkasten.nlsgen;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MultiLocaleStrings {

	protected Map<Locale, ResourceBundle> bundles = new HashMap<Locale, ResourceBundle>();

	protected MultiLocaleStrings() {
	}

	public void add(ResourceBundle bundle) {
		this.bundles.put(bundle.getLocale(), bundle);
	}

	protected String getMessage(Locale locale, String key) {
		ResourceBundle rb = this.bundles.get(locale);
		return rb != null ? rb.getString(key) : null;
	}
}
