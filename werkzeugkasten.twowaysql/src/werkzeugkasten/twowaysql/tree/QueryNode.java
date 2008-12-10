package werkzeugkasten.twowaysql.tree;

import werkzeugkasten.twowaysql.tree.loc.Locatable;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public interface QueryNode extends Locatable {

	NodeType getType();

	QueryNode getParent();

	void setParent(QueryNode node);

	Iterable<QueryNode> getChildren();

	void setChildren(Iterable<QueryNode> kids);

	<C> boolean accept(QueryTreeVisitor<C> visitor, C context);
}
