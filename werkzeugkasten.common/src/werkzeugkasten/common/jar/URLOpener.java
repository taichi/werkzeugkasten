/**
 * 
 */
package werkzeugkasten.common.jar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLOpener implements Opener {
	URL url;

	public URLOpener(URL url) {
		this.url = url;
	}

	public InputStream open() throws IOException {
		return url.openStream();
	}
}