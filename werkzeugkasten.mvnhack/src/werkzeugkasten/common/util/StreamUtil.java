package werkzeugkasten.common.util;

import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	public static void close(InputStream in) {
		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
