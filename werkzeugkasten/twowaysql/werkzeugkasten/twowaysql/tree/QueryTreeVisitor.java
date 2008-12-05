package werkzeugkasten.twowaysql.tree;

public interface QueryTreeVisitor<C> {

	boolean visit(TxtNode node, C context);

	boolean visit(ExpressionNode node, C context);

	boolean visit(BeginNode node, C context);

	boolean visit(IfNode node, C context);

	boolean visit(BindNode node, C context);

	boolean visit(InBindNode node, C context);

	boolean visit(TwoWayQuery node, C context);

}
