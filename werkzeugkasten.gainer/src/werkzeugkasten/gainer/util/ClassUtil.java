package werkzeugkasten.gainer.util;

import java.lang.reflect.Method;

public class ClassUtil {

	public static void invoke(Method m, Object obj, Object... args) {
		try {
			m.invoke(obj, args);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
