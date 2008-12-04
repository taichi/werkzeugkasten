package werkzeugkasten.twowaysql.tree;

public interface QueryNode {

	NodeType type();

	TextLocation position();
}
