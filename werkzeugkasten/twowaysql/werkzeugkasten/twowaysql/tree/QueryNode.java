package werkzeugkasten.twowaysql.tree;

public interface QueryNode {

	NodeType getType();

	TextLocation getLocation();

	QueryNode getParent();

	void setParent(QueryNode node);

	Iterable<QueryNode> getChildren();

	void setChildren(Iterable<QueryNode> kids);

	<P> void accept(QueryTreeVisitor<P> visitor, P parameter);
}
