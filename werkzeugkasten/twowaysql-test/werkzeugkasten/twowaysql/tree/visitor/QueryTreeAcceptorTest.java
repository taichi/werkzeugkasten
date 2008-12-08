package werkzeugkasten.twowaysql.tree.visitor;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import werkzeugkasten.twowaysql.tree.BeginNode;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.IfNode;
import werkzeugkasten.twowaysql.tree.InBindNode;
import werkzeugkasten.twowaysql.tree.QueryNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;

public class QueryTreeAcceptorTest {

	QueryNode tree;

	QueryTreeVisitor<StringBuilder> toString = new QueryTreeVisitor<StringBuilder>() {
		public void preVisit(QueryNode node, StringBuilder context) {
			context.append("(");
		}

		public void postVisit(QueryNode node, StringBuilder context) {
			context.append(")");
		}

		public boolean visit(TxtNode node, StringBuilder context) {
			context.append(node.getTxt());
			return true;
		}

		public boolean visit(TwoWayQuery node, StringBuilder context) {
			return true;
		}

		public boolean visit(ExpressionNode node, StringBuilder context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(BeginNode node, StringBuilder context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(IfNode node, StringBuilder context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(BindNode node, StringBuilder context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(InBindNode node, StringBuilder context) {
			// TODO Auto-generated method stub
			return false;
		}
	};

	@Before
	public void setUp() {
		tree = new TwoWayQuery();
		dig(tree, 4, 3);
	}

	protected QueryNode dig(QueryNode parent, int size, int depth) {
		if (0 < depth) {
			ArrayList<QueryNode> list = new ArrayList<QueryNode>();
			for (int c = 0; c < size; c++) {
				TxtNode child = txt(depth * 100 + c * 10, size);
				list.add(dig(child, size, depth - 1));
			}
			parent.setChildren(list);
		}
		return parent;
	}

	protected TxtNode txt(int i, int size) {
		TxtNode t = txt(i++);
		ArrayList<QueryNode> list = new ArrayList<QueryNode>(size);
		for (int c = 0; c < size; c++) {
			list.add(txt(i++));
		}
		t.setChildren(list);
		return t;
	}

	protected TxtNode txt(int i) {
		TxtNode t = new TxtNode();
		t.setTxt("[" + String.valueOf(i) + "]");
		return t;
	}

	@Test
	public void testAccept() {
		StringBuilder stb = new StringBuilder();
		QueryTreeAcceptor.accept(tree, toString, stb);
		assertNotNull(stb);
	}

	@Test
	public void testAcceptByTailRecursion() {
		StringBuilder stb = new StringBuilder();
		QueryTreeAcceptor.accept(tree, toString, stb);

		StringBuilder stb2 = new StringBuilder();
		QueryTreeAcceptor.acceptByTailRecursion(tree, toString, stb2);

		assertEquals(stb.toString(), stb2.toString());
	}

	QueryTreeVisitor<Void> pf = new QueryTreeVisitor<Void>() {

		public void postVisit(QueryNode node, Void context) {
			// TODO Auto-generated method stub

		}

		public void preVisit(QueryNode node, Void context) {
			// TODO Auto-generated method stub

		}

		public boolean visit(TwoWayQuery node, Void context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(TxtNode node, Void context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(ExpressionNode node, Void context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(BeginNode node, Void context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(IfNode node, Void context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(BindNode node, Void context) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean visit(InBindNode node, Void context) {
			// TODO Auto-generated method stub
			return false;
		}

	};

	@Test
	public void testAcceptByTailRecursionPf() {
		for (int depth = 3; depth < 8; depth++) {
			System.out.println("depth " + depth);
			for (int i = 11; 3 < i; i--) {
				// QueryNode tree = new TwoWayQuery();
				dig(tree, i, depth);

				System.out.printf("width %2d ", i);
				long start = System.currentTimeMillis();
				QueryTreeAcceptor.accept(tree, pf, null);
				System.out.printf("%4d", System.currentTimeMillis() - start);

				System.out.print(" ");

				start = System.currentTimeMillis();
				QueryTreeAcceptor.acceptByTailRecursion(tree, pf, null);
				System.out.printf("%4d", System.currentTimeMillis() - start);
				System.out.println();
			}
		}
	}

}
