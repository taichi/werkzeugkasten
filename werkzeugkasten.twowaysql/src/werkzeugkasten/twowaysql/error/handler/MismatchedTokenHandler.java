package werkzeugkasten.twowaysql.error.handler;

import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.RecognitionException;

import werkzeugkasten.twowaysql.error.DefaultQueryProblem;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.error.RecognitionExceptionHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public abstract class MismatchedTokenHandler implements
		RecognitionExceptionHandler {

	protected String ruleName;

	public MismatchedTokenHandler(String ruleName) {
		this.ruleName = ruleName;
	}

	public Class<? extends RecognitionException> getHadleType() {
		return MismatchedTokenException.class;
	}

	public QueryProblem handle(RecognitionException cause) {
		MismatchedTokenException ex = (MismatchedTokenException) cause;
		DefaultQueryProblem qp = new DefaultQueryProblem(ex);
		String msg = String.format(Messages.MISMATCHED_TOKEN, ruleName,
				selectExpected(ex.expecting), Messages
						.getTokenErrorDisplay(ex.token));
		qp.setMessage(msg);
		return qp;
	}

	protected abstract String selectExpected(int expecting);

}
