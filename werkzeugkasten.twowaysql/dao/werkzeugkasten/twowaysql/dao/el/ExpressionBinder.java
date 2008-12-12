package werkzeugkasten.twowaysql.dao.el;

import java.sql.PreparedStatement;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.jdbc.Binders;
import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;

public class ExpressionBinder<C> implements Binder {

	protected C context;
	protected Expression<C> expression;

	public ExpressionBinder(C context, Expression<C> expression) {
		this.context = context;
		this.expression = expression;
	}

	@Override
	public void bind(PreparedStatement ps, int index)
			throws SQLRuntimeException {
		Object result = expression.eval(this.context);
		Binder b = Binders.find(result);
		b.bind(ps, index);
	}
}
