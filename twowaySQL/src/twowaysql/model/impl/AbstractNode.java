package twowaysql.model.impl;

import java.util.ArrayList;
import java.util.List;

import twowaysql.TwowaySQLContext;
import twowaysql.model.Node;

public abstract class AbstractNode implements Node {

	protected Node root;

	protected List<Node> kids = new ArrayList<Node>();

	@Override
	public Node getRoot() {
		return this.root;
	}

	@Override
	public void setRoot(Node node) {
		this.root = node;
	}

	@Override
	public void add(Node child) {
		this.kids.add(child);
	}

	@Override
	public List<Node> children() {
		return this.kids;
	}

	@Override
	public void execute(TwowaySQLContext context) {
		for (Node n : this.kids) {
			n.execute(context);
		}
	}

}
