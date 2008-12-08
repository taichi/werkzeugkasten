package werkzeugkasten.twowaysql.tree;

import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class TxtNode extends AbstractQueryNode {

	protected String txt;

	public NodeType getType() {
		return NodeType.TXTNODE;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getTxt() {
		return this.txt;
	}

	public <C> boolean accept(QueryTreeVisitor<C> visitor, C context) {
		return visitor.visit(this, context);
	};

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(super.toString());
		stb.append('{');
		stb.append(getTxt());
		stb.append('}');
		return stb.toString();
	}
}
