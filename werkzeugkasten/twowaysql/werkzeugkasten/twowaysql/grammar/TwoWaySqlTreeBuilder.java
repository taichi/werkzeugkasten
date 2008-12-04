package werkzeugkasten.twowaysql.grammar;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeAdaptor;

public class TwoWaySqlTreeBuilder extends CommonTreeAdaptor {

	public Object create(Token token) {
		if (token != null) {
			System.out.println("token " + token.getText());
		}
		return super.create(token);
	}

	public Object create(int ttype, Token token, String text) {
		System.out.printf("type : [%s] token:[%s] text:[%s]\n", ttype, token,
				text);
		return super.create(ttype, token, text);
	}

	public Object create(int ttype, String text) {
		System.out.printf("type : [%s] text:[%s]\n", ttype, text);
		return super.create(ttype, text);
	}
}
