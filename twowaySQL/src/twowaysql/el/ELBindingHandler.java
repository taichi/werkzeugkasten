package twowaysql.el;

import java.sql.PreparedStatement;

import twowaysql.BindingHandler;

public class ELBindingHandler implements BindingHandler {

	protected Expression expression;

	public ELBindingHandler(String source) {
		this.expression = ExpressionFactory.instance().factory(source);
	}

	@Override
	public void bind(PreparedStatement ps, int index) {

	}
}
