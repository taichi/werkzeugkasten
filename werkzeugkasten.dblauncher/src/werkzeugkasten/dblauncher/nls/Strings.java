package werkzeugkasten.dblauncher.nls;

import org.eclipse.osgi.util.NLS;

public class Strings extends NLS {

	public static String LABEL_USE_H2_PLUGIN;

	public static String LABEL_IS_DEBUG;

	public static String LABEL_BASE_DIR;

	public static String LABEL_DB_PORTNO;

	public static String LABEL_WEB_PORTNO;

	public static String LABEL_USER;

	public static String LABEL_PASSWORD;

	public static String ERR_ONLY_NUMERIC;

	public static String DEC_LABEL_FMT;

	public static String MSG_START_SERVER;

	public static String LABEL_START;

	public static String LABEL_STOP;

	static {
		Class<?> clazz = Strings.class;
		NLS.initializeMessages(clazz.getName(), clazz);
	}

}
