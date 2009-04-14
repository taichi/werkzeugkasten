package werkzeugkasten.twowaysql.editor.formatter;

import java.util.regex.Pattern;

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
	public void preVisit(QueryNode node, FormatContext context) {
	}

	@Override
	public void postVisit(QueryNode node, FormatContext context) {
	}

	@Override
	public boolean visit(TwoWayQuery node, FormatContext context) {
		return true;
	}

	@Override
	public boolean visit(TxtNode node, FormatContext context) {
		return true;
	}

	@Override
	public boolean visit(ExpressionNode node, FormatContext context) {
		return true;
	}

	@Override
	public boolean visit(BeginNode node, FormatContext context) {
		context.add(1);
		return true;
	}

	@Override
	public boolean visit(IfNode node, FormatContext context) {
		context.add(1);
		return true;
	}

	@Override
	public boolean visit(ElseNode node, FormatContext context) {
		return true;
	}

	@Override
	public boolean visit(BindNode node, FormatContext context) {
		return true;
	}

	@Override
	public boolean visit(InBindNode node, FormatContext context) {
		return true;
	}

	static final Pattern whitespaces = Pattern.compile("\\s+",
			Pattern.CASE_INSENSITIVE);

	public static String replaceWhitespaces(String string) {
		return whitespaces.matcher(string).replaceAll(" ");
	}

}
