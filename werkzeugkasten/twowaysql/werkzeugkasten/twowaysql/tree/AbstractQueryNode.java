package werkzeugkasten.twowaysql.tree;


public abstract class AbstractQueryNode implements QueryNode {

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

}
