package werkzeugkasten.twowaysql.error.handler;

import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.RecognitionException;

import werkzeugkasten.twowaysql.error.DefaultQueryProblem;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.error.RecognitionExceptionHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class MismatchedSetHandler implements RecognitionExceptionHandler {

	protected String ruleName;
	protected String required;

	public MismatchedSetHandler(String ruleName, String required) {
		this.ruleName = ruleName;
		this.required = required;
	}

	@Override
	public Class<? extends RecognitionException> getHadleType() {
		return MismatchedSetException.class;
	}

	@Override
	public QueryProblem handle(RecognitionException ex) {
		DefaultQueryProblem qp = new DefaultQueryProblem(ex);
		String msg = String.format(Messages.MISMATCHEDSET, ruleName, required,
				Messages.getTokenErrorDisplay(ex.token));
		qp.setMessage(msg);
		return qp;
	}
}
