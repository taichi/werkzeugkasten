package org.handwerkszeug.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Streams {

	static final Logger LOG = Logger.getLogger(Streams.class.getName());

	public static abstract class using<STREAM extends Closeable, T extends Exception> {
		/**
		 * @param t
		 *            is trick parameter. needless to assign.
		 */
		@SuppressWarnings("unchecked")
		public using(T... t) {
			$(this, (Class<T>) t.getClass().getComponentType());
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

}
