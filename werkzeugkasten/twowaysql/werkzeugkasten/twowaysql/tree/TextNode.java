package werkzeugkasten.twowaysql.tree;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class TextNode implements QueryNode {

	protected LocationCalculator calculator = new LocationCalculator();

	protected TextLocation position;

	public TextLocation position() {
		return this.position;
	}

	public NodeType type() {
		return NodeType.TXTNODE;
	}

	public void append(Token token) {
		this.calculator.append(token);
	}

	public void append(CommonTree tree) {
		this.calculator.append(tree);
	}

	public void freeze() {
		this.position = this.calculator.freeze();
	}
}
