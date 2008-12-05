package werkzeugkasten.twowaysql.tree;

public interface QueryNode extends Locatable {

	NodeType getType();

	QueryNode getParent();

	void setParent(QueryNode node);

	Iterable<QueryNode> getChildren();

	void setChildren(Iterable<QueryNode> kids);

	<C> boolean accept(QueryTreeVisitor<C> visitor, C context);
}
