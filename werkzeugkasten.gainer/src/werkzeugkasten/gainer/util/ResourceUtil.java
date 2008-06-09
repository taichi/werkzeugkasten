package werkzeugkasten.gainer.util;

import java.net.URL;

public class ResourceUtil {

	public static URL getResource(String path) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		return cl.getResource(path);
	}
}
