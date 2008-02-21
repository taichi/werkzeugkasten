package werkzeugkasten.common.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isEmpty(Object s) {
		return s == null || s.toString().length() < 1;
	}

	public static String toString(Object s) {
		return toString(s, "");
	}

	public static String toString(Object s, String r) {
		return isEmpty(s) ? r : s.toString();
	}

	public static String replace(String template, Map<String, String> context) {
		String result = "";
		if (StringUtil.isEmpty(template) == false) {
			Pattern p = Pattern.compile("\\$\\{[^\\$\\{\\}]*\\}");
			StringBuffer stb = new StringBuffer(template);
			Matcher m = p.matcher(stb);
			int index = 0;
			while (index < stb.length() && m.find(index)) {
				String s = m.group();
				String v = toString(context.get(s.substring(2, s.length() - 1)));
				index = m.start() + v.length();
				stb.replace(m.start(), m.end(), v);
				m = p.matcher(stb);
			}
			result = stb.toString();
		}
		return result;
	}

}
