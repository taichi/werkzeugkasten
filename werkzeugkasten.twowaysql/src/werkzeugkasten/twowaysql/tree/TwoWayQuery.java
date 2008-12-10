package werkzeugkasten.twowaysql.tree;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class TwoWayQuery extends AbstractQueryNode {

	public NodeType getType() {
		return NodeType.ROOTNODE;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context);
	};
}
