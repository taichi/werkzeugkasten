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
	public static String action_addDirToBuildpath;
	public static String action_removeDirFromBuildpath;
	public static String menu_dirBuildpath;
	public static String DESC_DIR_BUILDPATH_CONTAINER;
	public static String UPDATE_BUILDPATH_CONTAINER;
	static {
		Object o = Activator.getDefault().getBundle().getHeaders().get(
				"Bundle-Localization");
		String s = o == null ? "plugin" : o.toString();
		NLS.initializeMessages(s, Strings.class);
	}
}