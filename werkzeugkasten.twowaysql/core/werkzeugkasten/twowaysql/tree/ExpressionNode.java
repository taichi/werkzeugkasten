package werkzeugkasten.twowaysql.tree;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class ExpressionNode extends TxtNode {

	public NodeType getType() {
		return NodeType.EXPRESSIONNODE;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context);
	};
}
