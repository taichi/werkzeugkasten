/**
 * 
 */
package werkzeugkasten.dirbuildpath.nls;

import org.eclipse.osgi.util.NLS;

import werkzeugkasten.dirbuildpath.Activator;

/**
 * @author taichi
 * 
 */
public class Strings {

	public static String plugin_label;
	public static String plugin_name;
	public static String plugin_author;
	public static String DESC_DIR_CLASSPATH_CONTAINER;
	public static String UPDATE_CLASSPATH_CONTAINER;
	public static String action_addDirToClasspath;
	public static String action_removeDirFromClasspath;
	public static String menu_dirClasspath;

	static {
		Object o = Activator.getDefault().getBundle().getHeaders().get(
				"Bundle-Localization");
		String s = o == null ? "plugin" : o.toString();
		NLS.initializeMessages(s, Strings.class);
	}
}