package werkzeugkasten.common.util;

public class StringUtil {

	public static boolean isEmpty(Object s) {
		return s == null || s.toString().length() < 1;
	}
}
