package werkzeugkasten.twowaysql.tree;

public class BindNode extends AbstractQueryNode {

	protected ExpressionNode expression;
	protected TxtNode skippedTxt;

	public NodeType getType() {
		return NodeType.BINDNODE;
	}

	public void setExpression(ExpressionNode node) {
		this.expression = node;
	}

	public ExpressionNode getExpression() {
		return this.expression;
	}

	public void setSkipped(TxtNode node) {
		this.skippedTxt = node;
	}

	public TxtNode getSkipped() {
		return this.skippedTxt;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context)
				&& getExpression().accept(visitor, context)
				&& getSkipped().accept(visitor, context);
	};
}
