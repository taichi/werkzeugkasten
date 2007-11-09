package werkzeugkasten.dblauncher.nls;

import org.eclipse.osgi.util.NLS;

public class Strings extends NLS {

	public static String LABEL_USE_DBLAUNCHER;

	public static String LABEL_DB_TYPE;

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

	public static String LABEL_BROWSE;

	public static String LABEL_USE_INTERNAL_WEBBROWSER;

	public static String LABEL_ADD_DRIVER_TO_BUILDPATH;

	static {
		Class<?> clazz = Strings.class;
		NLS.initializeMessages(clazz.getName(), clazz);
	}

}
