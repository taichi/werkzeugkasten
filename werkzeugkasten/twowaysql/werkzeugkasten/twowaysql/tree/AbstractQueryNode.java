package werkzeugkasten.twowaysql.tree;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

import werkzeugkasten.twowaysql.tree.loc.LocationCalculator;
import werkzeugkasten.twowaysql.tree.loc.TextLocation;

public abstract class AbstractQueryNode implements QueryNode {

	protected LocationCalculator calculator = new LocationCalculator();
	protected TextLocation location = TextLocation.UNKNOWN_LOCATION;

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

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append("[");
		stb.append(getType());
		stb.append("]");
		stb.append(getLocation().toString());
		return stb.toString();
	}
}
