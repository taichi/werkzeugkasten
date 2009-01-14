/**
 * 
 */
package werkzeugkasten.twowaysql.nls;

import java.util.Dictionary;
import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.twowaysql.Activator;

/**
 * @author taichi
 * 
 */
public class Strings {

	static final ResourceBundle bundle;
	public static String BUNDLE_VERSION;
	public static String ContentAssistProposal_label;
	public static String ContextPage_label;
	public static String Browse;

	static {
		Dictionary<?, ?> dic = Activator.getDefault().getBundle().getHeaders();
		Object o = dic.get("Bundle-Localization");
		String s = StringUtil.toString(o, "plugin");
		NLS.initializeMessages(s, Strings.class);
		bundle = ResourceBundle.getBundle(s);
		o = dic.get("Bundle-Version");
		BUNDLE_VERSION = StringUtil.toString(o, "0.0.0");
	}

	public static ResourceBundle getBundle() {
		return bundle;
	}
}