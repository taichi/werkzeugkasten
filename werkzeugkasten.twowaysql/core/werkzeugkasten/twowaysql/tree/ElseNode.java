package werkzeugkasten.twowaysql.tree;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class ElseNode extends AbstractQueryNode implements ContainSkippable {

	protected TxtNode maybeSkip;

	public NodeType getType() {
		return NodeType.IFNODE;
	}

	public void setMaybeSkip(TxtNode skip) {
		this.maybeSkip = skip;
	}

	public TxtNode getMaybeSkip() {
		return this.maybeSkip;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context);
	}
}
