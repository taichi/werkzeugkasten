package werkzeugkasten.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

	public static void close(OutputStream out) {
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static final int BUF_SIZE = 8096;

	public static void copy(InputStream in, OutputStream out) {
		byte[] buf = new byte[BUF_SIZE];
		try {
			while (true) {
				int len = in.read(buf, 0, BUF_SIZE);
				if (-1 < len) {
					out.write(buf, 0, len);
				} else {
					break;
				}
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static void copy(InputStream in, File dest) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(dest);
			copy(in, out);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			close(out);
		}
	}
}
