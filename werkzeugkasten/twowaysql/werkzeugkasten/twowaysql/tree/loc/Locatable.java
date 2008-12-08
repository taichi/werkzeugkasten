package werkzeugkasten.twowaysql.tree.loc;

import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.Token;

public interface Locatable {

	void update(Token token);

	void update(ParserRuleReturnScope tree);

	void freeze();

	TextLocation getLocation();
}
