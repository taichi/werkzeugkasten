package werkzeugkasten.twowaysql.tree.loc;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;


public interface Locatable {

	void update(Token token);

	void update(CommonTree tree);

	void freeze();

	TextLocation getLocation();
}
