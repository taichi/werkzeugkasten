package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.tree.BeginNode;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.IfNode;
import werkzeugkasten.twowaysql.tree.InBindNode;
import werkzeugkasten.twowaysql.tree.QueryNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class SqlTreeVisitor<EC> implements
		QueryTreeVisitor<SqlContext<EC>> {

	@Override
	public void preVisit(QueryNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postVisit(QueryNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean visit(TwoWayQuery node, SqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(TxtNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(ExpressionNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(BeginNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(IfNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(BindNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(InBindNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

}
