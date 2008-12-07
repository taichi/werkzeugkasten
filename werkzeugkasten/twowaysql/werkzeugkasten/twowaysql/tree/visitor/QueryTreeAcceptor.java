package werkzeugkasten.twowaysql.tree.visitor;

import java.util.LinkedList;

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

	protected interface Task {
		boolean execute();
	}

	public static <C> void acceptByTailRecursion(QueryNode tree,
			final QueryTreeVisitor<C> visitor, final C context) {
		LinkedList<Task> tasks = new LinkedList<Task>();
		LinkedList<QueryNode> trees = new LinkedList<QueryNode>();
		trees.addFirst(tree);
		addTask(tree, visitor, context, tasks);
		while (0 < tasks.size()) {
			QueryNode current = trees.removeFirst();
			tasks.removeFirst().execute();
			if (tasks.removeFirst().execute()) {
				for (QueryNode n : current.getChildren()) {
					trees.addFirst(n);
					addTask(n, visitor, context, tasks);
				}
			}
			tasks.removeFirst().execute();
		}
	}

	protected static <C> void addTask(final QueryNode tree,
			final QueryTreeVisitor<C> visitor, final C context,
			LinkedList<Task> list) {
		list.addFirst(new Task() {
			public boolean execute() {
				return tree.accept(visitor, context);
			}
		});
		list.addFirst(new Task() {
			public boolean execute() {
				visitor.preVisit(tree, context);
				return true;
			}
		});
		list.addLast(new Task() {
			public boolean execute() {
				visitor.postVisit(tree, context);
				return true;
			}
		});
	}
}
