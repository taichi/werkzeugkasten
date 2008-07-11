package werkzeugkasten.nlsgen.nls;

import org.eclipse.osgi.util.NLS;

public class Strings extends NLS {

	public static String GENERATE_CLASSES;

	public static String LABEL_GENERATOR_TYPE;

	public static String LABEL_DEST_PATH;

	public static String LABEL_ADD_RUNTIME;

	public static String MSG_NEED_RUNTIME;

	public static String LABEL_BROWSE;

	public static String MODIFY_CLASSES;

	static {
		Class<?> clazz = Strings.class;
		NLS.initializeMessages(clazz.getName(), clazz);
	}
}
