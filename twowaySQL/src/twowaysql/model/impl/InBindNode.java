package twowaysql.model.impl;

import java.util.List;

import twowaysql.TwowaySQLContext;
import twowaysql.el.Expression;
import twowaysql.el.ExpressionFactory;

public class InBindNode extends AbstractNode {

	protected Expression expression;

	public InBindNode(String source) {
		this.expression = ExpressionFactory.instance().factory(source);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(TwowaySQLContext context) {
		Object result = this.expression.invoke(context.getContextObject());
		if (result instanceof List) {
			List l = (List) result;

		}
	}
}
