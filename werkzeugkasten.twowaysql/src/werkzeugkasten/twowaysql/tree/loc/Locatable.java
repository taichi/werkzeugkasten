package werkzeugkasten.twowaysql.tree.loc;

import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.Token;

public interface Locatable {

	void update(Token token);

	void update(ParserRuleReturnScope scope);

	void freeze();

	TextLocation getLocation();
}
