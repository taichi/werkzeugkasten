package werkzeugkasten.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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

	public static URL toURL(File file) {
		try {
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		}
	}

	public static URL toURL(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		}
	}
}
