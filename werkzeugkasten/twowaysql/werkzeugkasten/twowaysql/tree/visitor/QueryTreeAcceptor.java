package werkzeugkasten.twowaysql.tree.visitor;

import werkzeugkasten.twowaysql.tree.QueryNode;

public class QueryTreeAcceptor {

	public static <P> void accept(QueryNode tree, QueryTreeVisitor<P> visitor,
			P parameter) {
		tree.accept(visitor, parameter);
		accept(tree.getChildren(), visitor, parameter);
	}

	public static <P> void accept(Iterable<QueryNode> kids,
			QueryTreeVisitor<P> visitor, P parameter) {
		for (QueryNode n : kids) {
			accept(n, visitor, parameter);
		}
	}
}
