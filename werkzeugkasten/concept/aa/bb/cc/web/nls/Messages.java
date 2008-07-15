package aa.bb.cc.web.nls;

import java.util.Locale;
import java.util.ResourceBundle;

/*
 * auto generated from Messages.properties by any eclipse plugin.
 */
public class Messages extends MessageHolder {

	private static final long serialVersionUID = -1809899457841590085L;

	public Messages() {
		add(ResourceBundle.getBundle(getClass().getName()));
	}

	public String ERROR_REQUIRED() {
		return ERROR_REQUIRED(Locale.getDefault());
	}

	public String ERROR_REQUIRED(Locale l) {
		return getMessage(l, "ERROR_REQUIRED");
	}
}
