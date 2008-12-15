package werkzeugkasten.twowaysql.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class IfNode extends AbstractQueryNode {

	protected ExpressionNode expression;
	protected TxtNode maybeSkip;
	protected List<IfNode> elseIfNodes = new ArrayList<IfNode>();
	protected Iterable<QueryNode> elseNode = Collections.emptyList();

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

	public void setElse(Iterable<QueryNode> tree) {
		if (tree != null) {
			this.elseNode = tree;
		}
	}

	public Iterable<QueryNode> getElse() {
		return this.elseNode;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context);
	}
}
