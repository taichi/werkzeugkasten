package werkzeugkasten.twowaysql.tree;

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
}
