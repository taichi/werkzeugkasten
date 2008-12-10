package werkzeugkasten.twowaysql.tree.visitor;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import werkzeugkasten.twowaysql.tree.QueryNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;

public class QueryTreeAcceptorTest {

	QueryNode tree;

	ToStringVisitor toString;

	@Before
	public void setUp() {
		tree = new TwoWayQuery();
		toString = new ToStringVisitor();
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
		System.out.println(stb.toString());
	}
}
