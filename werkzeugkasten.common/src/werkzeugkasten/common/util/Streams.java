package werkzeugkasten.common.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import werkzeugkasten.common.exception.IORuntimeException;

public class Streams {

	static final Logger LOG = Logger.getLogger(Streams.class.getName());

	public static abstract class using<STREAM extends Closeable, T extends Exception> {
		public using(Class<T> clazz) {
			$(this, clazz);
		}

		public abstract STREAM open() throws T;

		public abstract void handle(STREAM stream) throws T;

		public abstract void happen(T exception);
	}

	@SuppressWarnings("unchecked")
	static <STREAM extends Closeable, T extends Exception> void $(
			using<STREAM, T> _, Class<T> clazz) {
		STREAM in = null;
		try {
			in = _.open();
			_.handle(in);
		} catch (Exception e) {
			if (clazz.isAssignableFrom(e.getClass())) {
				_.happen((T) e);
			}
			throw new IllegalStateException(e);
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
			LOG.log(Level.WARNING, e.getLocalizedMessage(), e);
		}
	}

	public static final int BUF_SIZE = 128 * 128;

	/**
	 * @param in
	 * @param out
	 * @see FileUtil#copy(InputStream, java.io.File)
	 */
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
