package werkzeugkasten.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import werkzeugkasten.common.exception.IORuntimeException;

public class StreamUtil {

	public interface _<STREAM, T extends Throwable> {
		STREAM open() throws T;

		void handle(STREAM stream) throws T;

		void happen(T exception);

	}

	private interface D<STREAM> {
		void dispose(STREAM stream);
	}

	@SuppressWarnings("unchecked")
	private static <STREAM, T extends Throwable> void $(_<STREAM, T> _,
			D<STREAM> c) {
		STREAM in = null;
		try {
			in = _.open();
			_.handle(in);
		} catch (Throwable e) {
			_.happen((T) e);
		} finally {
			c.dispose(in);
		}
	}

	public static <STREAM extends InputStream, T extends Throwable> void is(
			_<STREAM, T> _) {
		$(_, new D<STREAM>() {
			public void dispose(STREAM stream) {
				close(stream);
			};
		});
	}

	public static <STREAM extends OutputStream, T extends Throwable> void os(
			_<STREAM, T> _) {
		$(_, new D<STREAM>() {
			public void dispose(STREAM stream) {
				close(stream);
			};
		});
	}

	public static void close(InputStream in) {
		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void close(OutputStream out) {
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void close(Reader r) {
		try {
			if (r != null) {
				r.close();
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void close(Writer w) {
		try {
			if (w != null) {
				w.close();
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
