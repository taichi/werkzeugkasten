package werkzeugkasten.twowaysql.tree;

public class ExpressionNode extends TextNode {

	public NodeType getType() {
		return NodeType.EXPRESSIONNODE;
	}

	public <P> void accept(QueryTreeVisitor<P> visitor, P parameter) {
		visitor.visit(this, parameter);
	};
}
