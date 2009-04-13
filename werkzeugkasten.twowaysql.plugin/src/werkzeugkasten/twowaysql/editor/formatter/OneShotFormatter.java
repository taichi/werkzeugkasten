package werkzeugkasten.twowaysql.editor.formatter;

import werkzeugkasten.twowaysql.tree.BeginNode;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ElseNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.IfNode;
import werkzeugkasten.twowaysql.tree.InBindNode;
import werkzeugkasten.twowaysql.tree.QueryNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class OneShotFormatter implements QueryTreeVisitor<FormatContext> {

	@Override
	public void postVisit(QueryNode node, FormatContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preVisit(QueryNode node, FormatContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean visit(TwoWayQuery node, FormatContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(TxtNode node, FormatContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(ExpressionNode node, FormatContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(BeginNode node, FormatContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(IfNode node, FormatContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(ElseNode node, FormatContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(BindNode node, FormatContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(InBindNode node, FormatContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
