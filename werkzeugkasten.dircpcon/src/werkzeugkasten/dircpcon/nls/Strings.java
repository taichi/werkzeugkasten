package werkzeugkasten.dircpcon.nls;

import org.eclipse.osgi.util.NLS;

public final class Strings extends NLS {

	public static String DESC_DIR_CLASSPATH_CONTAINER = "";

	static {
		Class<?> clazz = Strings.class;
		NLS.initializeMessages(clazz.getName(), clazz);
	}
}
