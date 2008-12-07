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
			// context.append("(");
		}

		public void postVisit(QueryNode node, StringBuilder context) {
			// context.append(")");
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
		dig(tree, 5, 3);
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
		System.out.println(stb.toString());
	}

	@Test
	public void testAcceptByTailRecursion() {
		StringBuilder stb = new StringBuilder();
		QueryTreeAcceptor.accept(tree, toString, stb);
		StringBuilder stb2 = new StringBuilder();
		QueryTreeAcceptor.acceptByTailRecursion(tree, toString, stb2);
		System.out.println(stb2.toString());

		assertEquals(stb, stb2);
	}

}
