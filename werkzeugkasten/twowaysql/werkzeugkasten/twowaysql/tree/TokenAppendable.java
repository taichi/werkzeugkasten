package werkzeugkasten.twowaysql.tree;

import org.antlr.runtime.CommonToken;

public interface TokenAppendable {

	void append(CommonToken token);

	void freeze();
}
