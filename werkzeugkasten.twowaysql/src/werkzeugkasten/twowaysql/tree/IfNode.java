package werkzeugkasten.twowaysql.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class IfNode extends AbstractQueryNode {

	protected ExpressionNode expression;
	protected String maybeSkip;
	protected List<QueryNode> elseIfNodes = new ArrayList<QueryNode>();
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

	public void setMaybeSkip(String skip) {
		this.maybeSkip = skip;
	}

	public String getMaybeSkip() {
		return this.maybeSkip;
	}

	public void addElseIf(IfNode node) {
		if (node != null) {
			this.elseIfNodes.add(node);
		}
	}

	public Iterable<QueryNode> getElseIfNodes() {
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
