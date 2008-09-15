package werkzeugkasten.weblauncher.nls;

import org.eclipse.osgi.util.NLS;

public class Strings extends NLS {

	public static String LABEL_USE_WEB_LAUNCHER;

	public static String LABEL_IS_DEBUG;

	public static String LABEL_BASE_DIR;

	public static String LABEL_WEB_PORTNO;

	public static String LABEL_ADD_LIBRARY_TO_BUILDPATH;

	public static String LABEL_SERVER_TYPE;

	public static String LABEL_CONTEXT_NAME;

	public static String LABEL_CONFIG;

	public static String LABEL_CHECK_SERVER;

	public static String LABEL_USE_INTERNAL_WEBBROWSER;

	public static String LABEL_IGNORE_PATTERN;

	public static String TOOLTIP_CHECK_SERVER;

	public static String ERR_ONLY_NUMERIC;

	public static String ERR_PATTERN_SYNTAX;

	public static String MSG_START_SERVER;

	public static String MSG_CONNECT_SERVER;

	public static String MSG_WAIT_FOR_SERVER;

	public static String MSG_OPEN_URL;

	public static String LABEL_START;

	public static String LABEL_STOP;

	public static String DEC_LABEL_FMT;

	public static String LABEL_BROWSE;

	public static String MSG_PROCESS_EXPORT;

	public static String MSG_EXPORT_ERRORS;

	static {
		Class<Strings> clazz = Strings.class;
		NLS.initializeMessages(clazz.getName(), clazz);
	}

}
