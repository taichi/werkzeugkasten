package werkzeugkasten.twowaysql.tree;

public interface QueryTreeVisitor<P> {

	void visit(TextNode node, P parameter);
}
