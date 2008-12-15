package werkzeugkasten.twowaysql.dao.el.mvel;

import werkzeugkasten.twowaysql.dao.el.Expression;
import werkzeugkasten.twowaysql.dao.el.ExpressionParser;

public class MVELExpressionParser implements ExpressionParser {

	@Override
	public <C> Expression<C> parse(String source) {
		return new MVELExpression<C>(source);
	}

}
