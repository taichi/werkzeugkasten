package werkzeugkasten.twowaysql.tree;

import java.util.ArrayList;
import java.util.List;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class IfNode extends AbstractQueryNode implements ContainSkippable {

	protected ExpressionNode expression;
	protected TxtNode maybeSkip;
	protected List<IfNode> elseIfNodes = new ArrayList<IfNode>();
	protected ElseNode elseNode;

	public NodeType getType() {
		return NodeType.IFNODE;
	}

	public void setExpression(ExpressionNode node) {
		this.expression = node;
	}

	public ExpressionNode getExpression() {
		return this.expression;
	}

	public void setMaybeSkip(TxtNode skip) {
		this.maybeSkip = skip;
	}

	public TxtNode getMaybeSkip() {
		return this.maybeSkip;
	}

	public void addElseIf(IfNode node) {
		if (node != null) {
			this.elseIfNodes.add(node);
		}
	}

	public Iterable<IfNode> getElseIfNodes() {
		return this.elseIfNodes;
	}

	public void setElse(ElseNode tree) {
		if (tree != null) {
			this.elseNode = tree;
		}
	}

	public ElseNode getElse() {
		return this.elseNode;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context);
	}
}
