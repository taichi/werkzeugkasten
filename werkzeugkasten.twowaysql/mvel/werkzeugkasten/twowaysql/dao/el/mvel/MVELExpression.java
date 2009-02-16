package werkzeugkasten.twowaysql.dao.el.mvel;

import java.io.Serializable;

import org.mvel2.MVEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.twowaysql.Markers;
import werkzeugkasten.twowaysql.dao.el.Expression;
import werkzeugkasten.twowaysql.dao.el.mvel.nls.Messages;

public class MVELExpression<C> implements Expression<C> {

	static final Logger LOG = LoggerFactory.getLogger(MVELExpression.class);

	protected String source;
	protected Serializable compiled;

	public MVELExpression(String source) {
		this.source = source;
		if (LOG.isDebugEnabled()) {
			LOG.debug(Markers.INTERFACE, Messages.PARSE, source);
		}
		this.compiled = MVEL.compileExpression(source);
	}

	@Override
	public String getSource() {
		return this.source;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <R> R eval(C context) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(Markers.INTERFACE, Messages.EVAL, this.source, context);
		}
		return (R) MVEL.executeExpression(this.compiled, context);
	}

}
