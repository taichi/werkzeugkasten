package werkzeugkasten.twowaysql.dao.el.mvel.nls;

import werkzeugkasten.nlsgen.SingleLocaleStrings;

public class Messages {

	public static String PARSE;
	public static String EVAL;

	static {
		Class<?> clazz = Messages.class;
		SingleLocaleStrings.load(clazz);
	}
}
