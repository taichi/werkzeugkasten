package werkzeugkasten.twowaysql.tree;

public interface QueryTreeVisitor<C> {

	void visit(TextNode node, C context);

	void visit(ExpressionNode node, C context);

	void visit(BeginNode node, C context);

	void visit(BindNode node, C context);
}
