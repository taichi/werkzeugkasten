package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.dao.el.Expression;
import werkzeugkasten.twowaysql.dao.el.ExpressionParser;
import werkzeugkasten.twowaysql.tree.BeginNode;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.IfNode;
import werkzeugkasten.twowaysql.tree.InBindNode;
import werkzeugkasten.twowaysql.tree.NodeType;
import werkzeugkasten.twowaysql.tree.QueryNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;
import werkzeugkasten.twowaysql.tree.loc.TextLocation;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class SqlTreeVisitor<EC> implements QueryTreeVisitor<SqlContext<EC>> {

	protected static final String SPC = " ";
	protected ExpressionParser parser;

	public SqlTreeVisitor(ExpressionParser parser) {
		this.parser = parser;
	}

	@Override
	public void preVisit(QueryNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postVisit(QueryNode node, SqlContext<EC> context) {
		if (NodeType.BEGINNODE.equals(node.getType())) {
			context.end();
		}
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
		context.append(SPC);
		context.begin();
		return true;
	}

	@Override
	public boolean visit(IfNode node, SqlContext<EC> context) {
		context.append(SPC);
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(BindNode node, SqlContext<EC> context) {
		context.append("?");
		Expression<EC> e = getExpression(node, context);
		Object o = e.eval(context.getExpressionContext());
		context.add(null);
		return false;
	}

	protected Expression<EC> getExpression(BindNode node, SqlContext<EC> context) {
		String part = getString(context, node.getLocation());
		Expression<EC> e = parser.parse(part);
		return e;
	}

	protected static <EC> String getString(SqlContext<EC> context,
			TextLocation loc) {
		String src = context.getTwoWayQuery().getSource();
		return src.substring(loc.startIndex(), loc.endIndex() + 1);
	}

	@Override
	public boolean visit(InBindNode node, SqlContext<EC> context) {
		// TODO Auto-generated method stub
		return false;
	}

}
