/**
 * 
 */
package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.Token;

import werkzeugkasten.nlsgen.SingleLocaleStrings;

/**
 * @author taichi
 * 
 */
public class Messages {

	public static String LABEL_ENDCOMMENT;
	public static String VIABLE_ENDCOMMENT;
	public static String NO_VIABLE_ALT;

	static {
		Class<?> clazz = Messages.class;
		SingleLocaleStrings.load(clazz);
	}

	protected static String getTokenErrorDisplay(Token t) {
		String s = t.getText();
		if (s == null) {
			if (t.getType() == Token.EOF) {
				s = "<EOF>";
			} else {
				s = "<" + t.getType() + ">";
			}
		}
		s = s.replaceAll("\n", "\\\\n");
		s = s.replaceAll("\r", "\\\\r");
		s = s.replaceAll("\t", "\\\\t");
		return "'" + s + "'";
	}
}