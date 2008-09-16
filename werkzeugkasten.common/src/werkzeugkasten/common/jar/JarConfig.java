/**
 * 
 */
package werkzeugkasten.common.jar;

import java.util.HashMap;
import java.util.Map;

public abstract class JarConfig {
	protected static final ExceptionHandler DEFAULT_HANDLER = new ExceptionHandler() {
		public void handle(Exception cause) {
			throw new IllegalStateException(cause);
		}
	};

	protected Map<Class<?>, ExceptionHandler> excetionHandlers = new HashMap<Class<?>, ExceptionHandler>();

	public ExceptionHandler find(Class<?> key) {
		ExceptionHandler eh = this.excetionHandlers.get(key);
		if (eh == null) {
			return DEFAULT_HANDLER;
		} else {
			return eh;
		}
	}

	public abstract boolean compress();
}