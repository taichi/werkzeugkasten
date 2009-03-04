/**
 * 
 */
package werkzeugkasten.twowaysql.nls;

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
	public static String MISMATCHED_TOKEN;
	public static String LABEL_TXT;
	public static String REQUIRED_TXT;
	public static String EARLY_EXIT;
	public static String REQUIRED_EXPRESSION;
	public static String LABEL_EXPRESSION;
	public static String VIABLE_ELSECOMMENT;
	public static String LABEL_ELSECOMMENT;
	public static String LABEL_BLOCKCOMMENT;
	public static String REQUIRED_BLOCKCOMMENT;
	public static String LABEL_LINECOMMENT;
	public static String REQUIRED_LINECOMMENT;
	public static String LABEL_ELSEIFBLOCKCOMMENT;
	public static String LABEL_ELSEIFLINECOMMENT;
	public static String VIABLE_BEGINCOMMENT;
	public static String LABEL_BEGINCOMMENT;
	public static String REQUIRED_BEGINCOMMENT;
	public static String LABEL_BINDCOMMENT;
	public static String LABEL_ELSENODE;
	public static String REQUIRED_ELSENODE;
	public static String LABEL_ELSEIFNODE;
	public static String REQUIRED_ELSEIFNODE;
	public static String LABEL_INBINDSKIPPED;
	public static String LABEL_INBIND;
	public static String REQUIRED_INBINDSKIPPED;
	public static String LABEL_IFCOMMENT;
	public static String REQUIRED_IFCOMMENT;
	public static String UNWANTED_TOKEN;
	public static String EXPRESSION_RESULT;
	public static String NULL_OR_EMPTY;
	public static String SKIPPED_TEXT;
	public static String LABEL_BINDINGNAME;
	public static String LABEL_IDENT;
	public static String BINDERFACTORY_FAILED;
	public static String MISSING_BINDERFACTORY;
	public static String SET_DEPENDENCY;
	public static String JDBC_STATEMENT_HANDLE;
	public static String JDBC_STATEMENT_PREPARE;
	public static String JDBC_RESULTSET_EXECUTE;
	public static String JDBC_RESULTSET_HANDLE;
	public static String JDBC_CONNECTION_HANDLE;
	public static String JDBC_CONNECTION_GET;
	public static String JDBC_CONNECTION_CLOSE;
	public static String JDBC_RESULTSET_CLOSE;
	public static String JDBC_STATEMENT_CLOSE;
	public static String LIFECYCLE_INITIALIZE;
	public static String LOAD_RESOURCE;
	public static String PARSE_TWOWAYSQL;
	public static String VISIT_TWOWAYSQL_TREE;
	public static String BIND;
	public static String BINDERPRODUCER_REGISTER_TYPE;
	public static String BINDERPRODUCER_REGISTER_NAME;
	public static String BINDERPRODUCER_FIND_TYPE;
	public static String BINDERPRODUCER_FIND_NAME;
	static {
		Class<?> clazz = Messages.class;
		SingleLocaleStrings.load(clazz);
	}

	public static String getTokenErrorDisplay(Token t) {
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