/**
 * 
 */
package werkzeugkasten.dircpcon.nls;

import org.eclipse.osgi.util.NLS;
import werkzeugkasten.dircpcon.Activator;

/**
 * @author taichi
 *
 */
public class Strings {

	public static String plugin_label;
	public static String plugin_name;
	public static String action_addToContainer;
	public static String plugin_author;
	public static String DESC_DIR_CLASSPATH_CONTAINER;

	static {
		Object o = Activator.getDefault().getBundle().getHeaders().get(
				"Bundle-Localization");
		String s = o == null ? "plugin" : o.toString();
		NLS.initializeMessages(s, Strings.class);
	}
}