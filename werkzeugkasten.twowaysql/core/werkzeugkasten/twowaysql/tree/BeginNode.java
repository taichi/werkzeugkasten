package werkzeugkasten.twowaysql.tree;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class BeginNode extends AbstractQueryNode {

	public NodeType getType() {
		return NodeType.BEGINNODE;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context);
	}
}
