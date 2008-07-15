package werkzeugkasten.nlsgen.nls;

import org.eclipse.osgi.util.NLS;

import werkzeugkasten.nlsgen.Activator;

public class Strings extends NLS {

	public static String GENERATE_CLASSES;

	public static String LABEL_GENERATOR_TYPE;

	public static String LABEL_DEST_PATH;

	public static String LABEL_ADD_RUNTIME;

	public static String MSG_NEED_RUNTIME;

	public static String LABEL_BROWSE;

	public static String MODIFY_CLASSES;

	public static String plugin_label;

	public static String plugin_author;

	public static String label_singlelocale;

	public static String label_multilocale;

	public static String plugin_name;

	public static String desc_singlelocale;

	public static String action_nls;

	public static String desc_multilocale;

	static {
		Object o = Activator.getDefault().getBundle().getHeaders().get(
				"Bundle-Localization");
		String s = o == null ? "plugin" : o.toString();
		NLS.initializeMessages(s, Strings.class);
	}
}
