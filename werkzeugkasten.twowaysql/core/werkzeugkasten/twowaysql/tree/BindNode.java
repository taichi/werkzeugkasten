package werkzeugkasten.twowaysql.tree;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class BindNode extends AbstractQueryNode {

	protected TxtNode bindingName;
	protected ExpressionNode expression;
	protected TxtNode skippedTxt;

	public NodeType getType() {
		return NodeType.BINDNODE;
	}

	public void setBindingName(TxtNode node) {
		this.bindingName = node;
	}

	public TxtNode getBindingName() {
		return this.bindingName;
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
		return visitor.visit(this, context);
	};
}
