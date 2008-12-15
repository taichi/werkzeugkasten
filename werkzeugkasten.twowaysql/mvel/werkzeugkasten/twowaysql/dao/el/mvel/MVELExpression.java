package werkzeugkasten.twowaysql.dao.el.mvel;

import java.io.Serializable;

import org.mvel2.MVEL;

import werkzeugkasten.twowaysql.dao.el.Expression;

public class MVELExpression<C> implements Expression<C> {

	protected String source;
	protected Serializable compiled;

	public MVELExpression(String source) {
		this.source = source;
		this.compiled = MVEL.compileExpression(source);
	}

	@Override
	public String getSource() {
		return this.source;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <R> R eval(C context) {
		return (R) MVEL.executeExpression(this.compiled, context);
	}

}
