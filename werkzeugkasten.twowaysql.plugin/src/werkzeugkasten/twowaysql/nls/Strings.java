/**
 * 
 */
package werkzeugkasten.twowaysql.nls;

import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

import werkzeugkasten.twowaysql.Activator;

/**
 * @author taichi
 * 
 */
public class Strings {

	static final ResourceBundle bundle;
	public static String ContentAssistProposal_label;
	public static String ContextPage_label;

	static {
		Object o = Activator.getDefault().getBundle().getHeaders().get(
				"Bundle-Localization");
		String s = o == null ? "plugin" : o.toString();
		NLS.initializeMessages(s, Strings.class);
		bundle = ResourceBundle.getBundle(s);
	}

	public static ResourceBundle getBundle() {
		return bundle;
	}
}