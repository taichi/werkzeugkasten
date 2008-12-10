package werkzeugkasten.twowaysql.tree.visitor;

import werkzeugkasten.twowaysql.tree.QueryNode;

public class QueryTreeAcceptor {

	public static <C> void accept(QueryNode tree, QueryTreeVisitor<C> visitor,
			C context) {
		visitor.preVisit(tree, context);
		if (tree.accept(visitor, context)) {
			accept(tree.getChildren(), visitor, context);
		}
		visitor.postVisit(tree, context);
	}

	public static <C> void accept(Iterable<QueryNode> kids,
			QueryTreeVisitor<C> visitor, C context) {
		for (QueryNode n : kids) {
			accept(n, visitor, context);
		}
	}
}
