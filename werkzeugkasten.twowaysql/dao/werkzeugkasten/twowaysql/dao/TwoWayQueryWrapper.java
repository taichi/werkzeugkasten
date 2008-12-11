package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public interface TwoWayQueryWrapper {

	String getSource();

	<C, V extends QueryTreeVisitor<C>> void accept(V visitor, C context);
}
