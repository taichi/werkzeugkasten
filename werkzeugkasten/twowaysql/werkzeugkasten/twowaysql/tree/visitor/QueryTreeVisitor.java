package werkzeugkasten.twowaysql.tree.visitor;

import werkzeugkasten.twowaysql.tree.BeginNode;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.IfNode;
import werkzeugkasten.twowaysql.tree.InBindNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;

public interface QueryTreeVisitor<C> {

	boolean visit(TwoWayQuery node, C context);

	boolean visit(TxtNode node, C context);

	boolean visit(ExpressionNode node, C context);

	boolean visit(BeginNode node, C context);

	boolean visit(IfNode node, C context);

	boolean visit(BindNode node, C context);

	boolean visit(InBindNode node, C context);

}
