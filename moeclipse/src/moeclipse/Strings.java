/**
 * 
 */
package moeclipse;

import org.eclipse.osgi.util.NLS;

/**
 * @author taichi
 * 
 */
public class Strings {

	public static String MODENAME;
	public static String LABEL_BROWSE;
	public static String LABEL_PICTUREPATH;

	static {
		Object o = Activator.getDefault().getBundle().getHeaders().get(
				"Bundle-Localization");
		String s = o == null ? "plugin" : o.toString();
		NLS.initializeMessages(s, Strings.class);
	}
}