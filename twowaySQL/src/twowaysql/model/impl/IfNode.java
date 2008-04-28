package twowaysql.model.impl;

import twowaysql.TwowaySQLContext;
import twowaysql.el.Expression;
import twowaysql.el.ExpressionFactory;

public class IfNode extends AbstractNode {

	protected Expression expression;

	public IfNode(String expression) {
		this.expression = ExpressionFactory.instance().factory(expression);
	}

	@Override
	public void execute(TwowaySQLContext context) {
		Object result = expression.invoke(context.getContextObject());
		if (result instanceof Boolean) {
			Boolean b = (Boolean) result;
			if (b) {
				super.execute(context);
			} else {
				invokeElseNodes(context);
			}
		}
		throw new IllegalStateException("result is not boolean, result : "
				+ result);
	}

	protected void invokeElseNodes(TwowaySQLContext context) {

	}
}
