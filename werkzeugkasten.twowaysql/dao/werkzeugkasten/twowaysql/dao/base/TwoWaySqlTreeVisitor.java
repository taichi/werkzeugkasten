package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.TwoWaySqlContext;
import werkzeugkasten.twowaysql.tree.BeginNode;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.IfNode;
import werkzeugkasten.twowaysql.tree.InBindNode;
import werkzeugkasten.twowaysql.tree.QueryNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class TwoWaySqlTreeVisitor<EC> implements
		QueryTreeVisitor<TwoWaySqlContext<EC>> {

	@Override
	public void preVisit(QueryNode node, TwoWaySqlContext<EC> context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postVisit(QueryNode node, TwoWaySqlContext<EC> context) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean visit(TwoWayQuery node, TwoWaySqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(TxtNode node, TwoWaySqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(ExpressionNode node, TwoWaySqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(BeginNode node, TwoWaySqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(IfNode node, TwoWaySqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(BindNode node, TwoWaySqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(InBindNode node, TwoWaySqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

}
