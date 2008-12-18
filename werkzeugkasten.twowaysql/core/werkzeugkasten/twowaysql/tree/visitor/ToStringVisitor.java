package werkzeugkasten.twowaysql.tree.visitor;

import werkzeugkasten.twowaysql.tree.BeginNode;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ContainSkippable;
import werkzeugkasten.twowaysql.tree.ElseNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.IfNode;
import werkzeugkasten.twowaysql.tree.InBindNode;
import werkzeugkasten.twowaysql.tree.QueryNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;
import werkzeugkasten.twowaysql.tree.loc.TextLocationUtil;

public class ToStringVisitor implements QueryTreeVisitor<StringBuilder> {

	protected String allOfString;

	public ToStringVisitor(String allOfString) {
		this.allOfString = allOfString;
	}

	protected String getTxt(QueryNode node) {
		String txt = TextLocationUtil.substring(allOfString, node);
		return "{" + txt + "}";
	}

	public void postVisit(QueryNode node, StringBuilder context) {
		context.append(')');
	}

	public void preVisit(QueryNode node, StringBuilder context) {
		context.append('(');
	}

	protected void defaultVisit(QueryNode node, StringBuilder context) {
		context.append(node.toString());
	}

	public boolean visit(TwoWayQuery node, StringBuilder context) {
		defaultVisit(node, context);
		return true;
	}

	public boolean visit(TxtNode node, StringBuilder context) {
		defaultVisit(node, context);
		context.append(getTxt(node));
		return true;
	}

	public boolean visit(ExpressionNode node, StringBuilder context) {
		defaultVisit(node, context);
		context.append(getTxt(node));
		return true;
	}

	public boolean visit(BeginNode node, StringBuilder context) {
		defaultVisit(node, context);
		return true;
	}

	public boolean visit(IfNode node, StringBuilder context) {
		defaultVisit(node, context);
		this.visit(node.getExpression(), context);
		processMaybeSkip(context, node);
		if (node.getElseIfNodes().iterator().hasNext()) {
			context.append("<ELSEIF ");
			QueryTreeAcceptor.accept(node.getElseIfNodes(), this, context);
			context.append(">");
		}
		ElseNode e = node.getElse();
		if (e != null) {
			this.visit(e, context);
		}
		return true;
	}

	protected void processMaybeSkip(StringBuilder context, ContainSkippable node) {
		TxtNode maybeSkip = node.getMaybeSkip();
		if (maybeSkip != null) {
			context.append("<MAYBESKIP[");
			this.visit(maybeSkip, context);
			context.append("]>");
		}
	}

	@Override
	public boolean visit(ElseNode node, StringBuilder context) {
		context.append("<ELSE ");
		processMaybeSkip(context, node);
		QueryTreeAcceptor.accept(node.getChildren(), this, context);
		context.append(">");
		return false;
	}

	public boolean visit(BindNode node, StringBuilder context) {
		defaultVisit(node, context);
		TxtNode bindingName = node.getBindingName();
		if (bindingName != null) {
			context.append("<BINDING ");
			this.visit(bindingName, context);
			context.append(">");
		}
		this.visit(node.getExpression(), context);
		context.append("<SKIPPED ");
		this.visit(node.getSkipped(), context);
		context.append(">");
		return true;
	}

	public boolean visit(InBindNode node, StringBuilder context) {
		visit((BindNode) node, context);
		return true;
	}

}
