package werkzeugkasten.twowaysql.tree.visitor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

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

	public static <C> void acceptByTailRecursion(QueryNode tree,
			final QueryTreeVisitor<C> visitor, final C context) {
		Deque<Iterator<QueryNode>> frames = new ArrayDeque<Iterator<QueryNode>>();
		QueryNode current = tree;
		do {
			visitor.preVisit(current, context);
			boolean is = false;
			if (current.accept(visitor, context)) {
				Iterator<QueryNode> kids = current.getChildren().iterator();
				if (is = kids.hasNext()) {
					frames.addFirst(kids);
				}
			}
			if (is == false) {
				visitor.postVisit(current, context);
			}
			QueryNode nextone = null;
			while (nextone == null && 0 < frames.size()) {
				Iterator<QueryNode> i = frames.getFirst();
				if (i.hasNext()) {
					nextone = i.next();
					break;
				} else {
					frames.removeFirst();
					visitor.postVisit(current.getParent(), context);
				}
			}
			current = nextone;
		} while (current != null);
	}
}
