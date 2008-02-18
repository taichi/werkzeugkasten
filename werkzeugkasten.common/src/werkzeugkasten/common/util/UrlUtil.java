package werkzeugkasten.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;

public class UrlUtil {

	public static InputStream open(URL url, Proxy proxy) {
		try {
			return url.openConnection(proxy).getInputStream();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
