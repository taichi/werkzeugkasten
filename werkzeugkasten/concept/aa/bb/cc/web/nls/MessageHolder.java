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

	public String getMessage(Locale l, String key) {
		ResourceBundle rb = this.bundles.get(l);
		if (rb != null) {
			return rb.getString(key);
		}
		return null;
	}
}
