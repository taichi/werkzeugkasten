package aa.bb.cc.web.nls;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class MessageHolder implements Serializable {

	protected Map<Locale, ResourceBundle> bundles = new HashMap<Locale, ResourceBundle>();

	public MessageHolder() {
	}

	public void add(ResourceBundle bundle) {
		this.bundles.put(bundle.getLocale(), bundle);
	}

	public String getMessage(Locale locale, String key) {
		ResourceBundle rb = this.bundles.get(locale);
		return rb != null ? rb.getString(key) : null;
	}
}
