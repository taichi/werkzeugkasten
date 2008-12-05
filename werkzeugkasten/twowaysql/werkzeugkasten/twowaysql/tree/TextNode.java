package werkzeugkasten.twowaysql.tree;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class TextNode extends AbstractQueryNode implements QueryNode {

	protected LocationCalculator calculator = new LocationCalculator();

	protected TextLocation position;

	public TextLocation getLocation() {
		return this.position;
	}

	public NodeType getType() {
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

	public <P> void accept(QueryTreeVisitor<P> visitor, P parameter) {
		visitor.visit(this, parameter);
	};
}
