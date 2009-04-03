package werkzeugkasten.twowaysql.grammar;

import java.util.HashMap;
import java.util.Map;

import werkzeugkasten.twowaysql.error.UnExpectedTokenTypeException;
import werkzeugkasten.twowaysql.nls.Messages;

public class GrammarUtil {

	static final Map<Integer, String> typeToView = new HashMap<Integer, String>();

	static {
		typeToView.put(TwoWaySqlParser.LT, "\\n");
		typeToView.put(TwoWaySqlParser.C_ST, "/*");
		typeToView.put(TwoWaySqlParser.QUOTED, "quoted");
		typeToView.put(TwoWaySqlParser.SYM_RP, ")");
		typeToView.put(TwoWaySqlParser.ELSE, "ELSE");
		typeToView.put(TwoWaySqlParser.SYM_LP, "(");
		typeToView.put(TwoWaySqlParser.C_LN_ST, "--");
		typeToView.put(TwoWaySqlParser.EOF, "<EOF>");
		typeToView.put(TwoWaySqlParser.C_ED, "*/");
		typeToView.put(TwoWaySqlParser.IF, "IF");
		typeToView.put(TwoWaySqlParser.WHITE_SPACES, " ");
		typeToView.put(TwoWaySqlParser.ELSEIF, "ELSEIF");
		typeToView.put(TwoWaySqlParser.IN, "IN");
		typeToView.put(TwoWaySqlParser.BEGIN, "BEGIN");
		typeToView.put(TwoWaySqlParser.IDENT, Messages.LABEL_IDENT);
		typeToView.put(TwoWaySqlParser.MAYBE_SKIP, "AND/OR");
		typeToView.put(TwoWaySqlParser.SYMBOLS, "'*' | '/' | '-' | '#'");
		typeToView.put(TwoWaySqlParser.SYM_C, ",");
		typeToView.put(TwoWaySqlParser.END, "END");
		typeToView.put(TwoWaySqlParser.SYM_BIND, "?");
		typeToView.put(TwoWaySqlParser.C_LN_ED, "\\n");

		// fragments
		// typeToView.put(TwoWaySqlParser.CHAR, "");
		// typeToView.put(TwoWaySqlParser.SYM_Q, "\"");
		// typeToView.put(TwoWaySqlParser.AND, "AND");
		// typeToView.put(TwoWaySqlParser.OR, "OR");
		// typeToView.put(TwoWaySqlParser.WS, " ");
		// typeToView.put(TwoWaySqlParser.LN_N, "\\n");
		// typeToView.put(TwoWaySqlParser.LN_R, "\\r");
	}

	public static String to(int type) {
		Integer i = type;
		String view = typeToView.get(i);
		if (view == null || view.isEmpty()) {
			throw new UnExpectedTokenTypeException(type);
		}
		return view;
	}
}
