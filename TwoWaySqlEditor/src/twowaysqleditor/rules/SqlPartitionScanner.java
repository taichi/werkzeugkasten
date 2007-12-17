package twowaysqleditor.rules;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class SqlPartitionScanner extends RuleBasedPartitionScanner {
	public static final String SQL_IF = "__sql_if";
	public static final String SQL_ELSE = "__sql_else";
	public static final String SQL_BEGIN = "__sql_begin";
	public static final String SQL_END = "__sql_end";
	public static final String SQL_BIND = "__sql_bind";
	public static final String SQL_COMMENT = "__sql_comment";

	public SqlPartitionScanner() {
		IToken sqlIf = new Token(SQL_IF);
		IToken sqlElse = new Token(SQL_ELSE);
		IToken sqlBegin = new Token(SQL_BEGIN);
		IToken sqlEnd = new Token(SQL_END);
		IToken sqlBind = new Token(SQL_BIND);
		IToken sqlComment = new Token(SQL_COMMENT);

		IPredicateRule[] rules = { new SingleLineRule("/*IF", "*/", sqlIf),
				new SingleLineRule("-- ELSE", null, sqlElse),
				new SingleLineRule("/*END", "*/", sqlEnd),
				new BeginRule(sqlBegin), new BindRule(sqlBind),
				new MultiLineRule("/*", "*/", sqlComment) };

		setPredicateRules(rules);
	}
}
