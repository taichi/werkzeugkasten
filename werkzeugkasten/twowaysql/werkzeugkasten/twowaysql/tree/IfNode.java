package werkzeugkasten.twowaysql.tree;

import java.util.LinkedList;

public class IfNode extends AbstractQueryNode {

	protected ExpressionNode expression;
	protected LinkedList<IfNode> elseIfNodes = new LinkedList<IfNode>();
	protected LinkedList<QueryNode> elseNode;

	public NodeType getType() {
		return NodeType.IFNODE;
	}

	public void setExpression(ExpressionNode node) {
		this.expression = node;
	}

	public ExpressionNode getExpression() {
		return this.expression;
	}

	public void addElseIf(IfNode node) {
		this.elseIfNodes.add(node);
	}

	public void setElse(LinkedList<QueryNode> tree) {
		this.elseNode = tree;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context)
				&& getExpression().accept(visitor, context);
	}
}
