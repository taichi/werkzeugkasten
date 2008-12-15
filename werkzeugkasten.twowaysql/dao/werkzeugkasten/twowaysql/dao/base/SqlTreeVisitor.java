package werkzeugkasten.twowaysql.dao.base;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.common.util.CollectionUtil;
import werkzeugkasten.twowaysql.dao.BinderProducer;
import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.dao.el.Expression;
import werkzeugkasten.twowaysql.dao.el.ExpressionParser;
import werkzeugkasten.twowaysql.nls.Messages;
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
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeAcceptor;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class SqlTreeVisitor<EC> implements QueryTreeVisitor<SqlContext<EC>> {

	static final Logger LOG = LoggerFactory.getLogger(SqlTreeVisitor.class);

	protected static final String SPC = " ";
	protected ExpressionParser parser;
	protected BinderProducer factory;

	public SqlTreeVisitor(ExpressionParser parser, BinderProducer factory) {
		this.parser = parser;
		this.factory = factory;
	}

	@Override
	public void preVisit(QueryNode node, SqlContext<EC> context) {
	}

	@Override
	public void postVisit(QueryNode node, SqlContext<EC> context) {
		if (NodeType.BEGINNODE.equals(node.getType())) {
			context.end();
		}
	}

	@Override
	public boolean visit(TwoWayQuery node, SqlContext<EC> context) {
		return true;
	}

	@Override
	public boolean visit(TxtNode node, SqlContext<EC> context) {
		context.append(getString(context, node.getLocation()));
		return false;
	}

	@Override
	public boolean visit(ExpressionNode node, SqlContext<EC> context) {
		Object bool = eval(node, context);
		if (LOG.isDebugEnabled()) {
			String s = getString(context, node.getLocation());
			LOG.debug(String.format(Messages.EXPRESSION_RESULT, s, bool));
		}
		if (bool instanceof Boolean) {
			return ((Boolean) bool).booleanValue();
		} else if (bool instanceof String) {
			String str = (String) bool;
			return Boolean.parseBoolean(str);
		}
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
		if (visit(node.getExpression(), context)) {
			if (context.isConcluded() == false) {
				context.append(getString(context, node.getMaybeSkip()
						.getLocation()));
				context.append(SPC);
			}
			context.conclude();
			return true;
		}
		for (IfNode in : node.getElseIfNodes()) {
			if (visit(in.getExpression(), context)) {
				context.conclude();
				QueryTreeAcceptor.accept(in.getChildren(), this, context);
				return false;
			}
		}
		if (node.getElse().iterator().hasNext()) {
			context.conclude();
			QueryTreeAcceptor.accept(node.getElse(), this, context);
		}
		return false;
	}

	@Override
	public boolean visit(BindNode node, SqlContext<EC> context) {
		context.append(" ? ");
		Object o = eval(node, context);
		context.add(this.factory.produce(o));
		return false;
	}

	protected Object eval(BindNode node, SqlContext<EC> context) {
		return eval(node.getExpression(), context);
	}

	protected Object eval(ExpressionNode node, SqlContext<EC> context) {
		String part = getString(context, node.getLocation());
		Expression<EC> e = parser.parse(part);
		return e.eval(context.getExpressionContext());
	}

	protected static <EC> String getString(SqlContext<EC> context,
			TextLocation loc) {
		String src = context.getTwoWayQuery().getSource();
		return src.substring(loc.startIndex(), loc.endIndex() + 1);
	}

	@Override
	public boolean visit(InBindNode node, SqlContext<EC> context) {
		Object maybeList = eval(node, context);
		List<?> list = CollectionUtil.toList(maybeList);
		context.append(" IN(");
		if (list != null && 0 < list.size()) {
			for (Iterator<?> i = list.iterator(); i.hasNext();) {
				context.add(factory.produce(i.next()));
				context.append("?");
				if (i.hasNext()) {
					context.append(",");
				}
			}
		} else {
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(Messages.EXPRESSION_RESULT, getString(
						context, node.getLocation()), Messages.NULL_OR_EMPTY));
			}
			context.append("null");
		}
		context.append(") ");
		return false;
	}

}