package werkzeugkasten.twowaysql.dao.base;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.common.util.CollectionUtil;
import werkzeugkasten.common.util.ConverterUtil;
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
import werkzeugkasten.twowaysql.tree.loc.Locatable;
import werkzeugkasten.twowaysql.tree.loc.TextLocationUtil;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeAcceptor;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class SqlTreeVisitor<EC> implements QueryTreeVisitor<SqlContext<EC>> {

	static final Logger LOG = LoggerFactory.getLogger(SqlTreeVisitor.class);

	protected static final String SPC = " ";
	protected ExpressionParser parser;
	protected BinderProducer producer;

	public SqlTreeVisitor(ExpressionParser parser, BinderProducer producer) {
		this.parser = parser;
		this.producer = producer;
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
		context.append(getString(context, node));
		return false;
	}

	@Override
	public boolean visit(ExpressionNode node, SqlContext<EC> context) {
		Object bool = eval(node, context);
		if (LOG.isDebugEnabled()) {
			String s = getString(context, node);
			LOG.debug(String.format(Messages.EXPRESSION_RESULT, s, bool));
		}
		Boolean is = ConverterUtil.convert(bool, Boolean.class);
		return is != null ? is : false;
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
				TxtNode maybeSkip = node.getMaybeSkip();
				if (maybeSkip != null) {
					context.append(getString(context, maybeSkip));
					context.append(SPC);
				}
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
		if (LOG.isDebugEnabled()) {
			String s = getString(context, node.getSkipped());
			LOG.debug(String.format(Messages.SKIPPED_TEXT, s));
		}
		Object o = eval(node, context);
		if (o == null) {
			context.append(" null ");
		} else {
			context.append(" ? ");
			context.add(this.producer.produce(context, node, o));
		}
		return false;
	}

	protected Object eval(BindNode node, SqlContext<EC> context) {
		return eval(node.getExpression(), context);
	}

	protected Object eval(ExpressionNode node, SqlContext<EC> context) {
		String part = getString(context, node);
		Expression<EC> e = parser.parse(part);
		return e.eval(context.getExpressionContext());
	}

	protected static <EC> String getString(SqlContext<EC> context, Locatable loc) {
		String src = context.getTwoWayQuery().getSource();
		return TextLocationUtil.substring(src, loc);
	}

	@Override
	public boolean visit(InBindNode node, SqlContext<EC> context) {
		if (LOG.isDebugEnabled()) {
			String s = getString(context, node.getSkipped());
			LOG.debug(String.format(Messages.SKIPPED_TEXT, s));
		}
		Object maybeList = eval(node, context);
		List<?> list = CollectionUtil.toList(maybeList);
		context.append(" IN(");
		if (list != null && 0 < list.size()) {
			for (Iterator<?> i = list.iterator(); i.hasNext();) {
				context.add(producer.produce(context, node, i.next()));
				context.append("?");
				if (i.hasNext()) {
					context.append(",");
				}
			}
		} else {
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(Messages.EXPRESSION_RESULT, getString(
						context, node), Messages.NULL_OR_EMPTY));
			}
			context.append("null");
		}
		context.append(") ");
		return false;
	}

}