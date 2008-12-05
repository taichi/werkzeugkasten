package werkzeugkasten.twowaysql.tree;

public class BeginNode extends AbstractQueryNode {

	public NodeType getType() {
		return NodeType.BEGINNODE;
	}

	public <C> void accept(QueryTreeVisitor<C> visitor, C context) {
		visitor.visit(this, context);
	}
}
