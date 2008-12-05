package werkzeugkasten.twowaysql.tree;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public abstract class AbstractQueryNode implements QueryNode {

	protected LocationCalculator calculator = new LocationCalculator();
	protected TextLocation location;

	protected QueryNode parent;
	protected Iterable<QueryNode> kids;

	@Override
	public QueryNode getParent() {
		return this.parent;
	}

	@Override
	public void setParent(QueryNode node) {
		this.parent = node;
	}

	@Override
	public Iterable<QueryNode> getChildren() {
		return this.kids;
	}

	@Override
	public void setChildren(Iterable<QueryNode> kids) {
		for (QueryNode qn : kids) {
			qn.setParent(this);
		}
		this.kids = kids;
	}

	@Override
	public void update(Token token) {
		this.calculator.update(token);
	}

	@Override
	public void update(CommonTree tree) {
		this.calculator.update(tree);
	}

	@Override
	public void freeze() {
		this.location = this.calculator.freeze();
	}

	@Override
	public TextLocation getLocation() {
		return this.location;
	}
}
