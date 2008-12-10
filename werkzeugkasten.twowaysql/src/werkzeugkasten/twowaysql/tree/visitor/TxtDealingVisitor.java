package werkzeugkasten.twowaysql.tree.visitor;

import werkzeugkasten.twowaysql.tree.BeginNode;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.IfNode;
import werkzeugkasten.twowaysql.tree.InBindNode;
import werkzeugkasten.twowaysql.tree.QueryNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;
import werkzeugkasten.twowaysql.tree.loc.TextLocation;

public class TxtDealingVisitor implements QueryTreeVisitor<String> {

	public void preVisit(QueryNode node, String context) {
	}

	public void postVisit(QueryNode node, String context) {
	}

	public boolean visit(TwoWayQuery node, String context) {
		return true;
	}

	protected void setTxt(TxtNode node, String context) {
		TextLocation loc = node.getLocation();
		node.setTxt(context.substring(loc.startIndex(), loc.endIndex() + 1));
	}

	public boolean visit(TxtNode node, String context) {
		setTxt(node, context);
		return true;
	}

	public boolean visit(ExpressionNode node, String context) {
		setTxt(node, context);
		return true;
	}

	public boolean visit(BeginNode node, String context) {
		return true;
	}

	public boolean visit(IfNode node, String context) {
		setTxt(node.getExpression(), context);
		QueryTreeAcceptor.accept(node.getElseIfNodes(), this, context);
		QueryTreeAcceptor.accept(node.getElse(), this, context);
		return true;
	}

	public boolean visit(BindNode node, String context) {
		setTxt(node.getExpression(), context);
		setTxt(node.getSkipped(), context);
		return true;
	}

	public boolean visit(InBindNode node, String context) {
		setTxt(node.getExpression(), context);
		setTxt(node.getSkipped(), context);
		return true;
	}

}
