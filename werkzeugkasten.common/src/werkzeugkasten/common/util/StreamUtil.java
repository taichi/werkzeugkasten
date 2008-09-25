package werkzeugkasten.common.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import werkzeugkasten.common.exception.IORuntimeException;

public class StreamUtil {

	public static abstract class using<STREAM extends Closeable, T extends Throwable> {
		public using() {
			$(this);
		}

		public abstract STREAM open() throws T;

		public abstract void handle(STREAM stream) throws T;

		public abstract void happen(T exception);
	}

	@SuppressWarnings("unchecked")
	static <STREAM extends Closeable, T extends Throwable> void $(
			using<STREAM, T> _) {
		STREAM in = null;
		try {
			in = _.open();
			_.handle(in);
		} catch (Throwable e) {
			_.happen((T) e);
		} finally {
			close(in);
		}
	}

	public static void close(Closeable c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static final int BUF_SIZE = 128 * 128;

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
			throw new IORuntimeException(e);
		}
	}

	public static void copy(InputStream in, File dest) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(dest);
			copy(in, out);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		} finally {
			close(out);
		}
	}

	public static String readText(InputStream in) {
		try {
			StringBuilder stb = new StringBuilder();
			BufferedReader buf = new BufferedReader(new InputStreamReader(in));
			while (buf.ready()) {
				stb.append(buf.readLine());
			}
			return stb.toString();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}
}
