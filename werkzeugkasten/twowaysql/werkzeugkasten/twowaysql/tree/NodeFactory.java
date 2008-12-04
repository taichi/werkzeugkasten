package werkzeugkasten.twowaysql.tree;

public class NodeFactory {

	private NodeFactory() {
	}

	public static NodeFactory getInstance() {
		return new NodeFactory();
	}

	public TextNode textNode() {
		return new TextNode();
	}
}
