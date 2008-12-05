package werkzeugkasten.twowaysql.tree;

public class InBindNode extends BindNode {

	public NodeType getType() {
		return NodeType.INBINDNODE;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context)
				&& getExpression().accept(visitor, context)
				&& getSkipped().accept(visitor, context);
	};
}
