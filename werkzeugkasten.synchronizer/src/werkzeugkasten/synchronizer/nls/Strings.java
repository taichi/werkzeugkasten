package werkzeugkasten.synchronizer.nls;

import org.eclipse.osgi.util.NLS;

public class Strings extends NLS {

	public static String MSG_START_SERVER;

	public static String MSG_STOP_SERVER;

	public static String MSG_REFRESH_RESOURCE;

	public static String LABEL_START;

	public static String LABEL_STOP;

	static {
		Class<Strings> clazz = Strings.class;
		NLS.initializeMessages(clazz.getName(), clazz);
	}
}
