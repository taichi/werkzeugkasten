package werkzeugkasten.twowaysql.error.handler;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.UnwantedTokenException;

import werkzeugkasten.twowaysql.error.DefaultQueryProblem;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.error.RecognitionExceptionHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public abstract class UnwantedTokenHandler implements
		RecognitionExceptionHandler {

	protected String ruleName;

	public UnwantedTokenHandler(String ruleName) {
		this.ruleName = ruleName;
	}

	public Class<? extends RecognitionException> getHadleType() {
		return UnwantedTokenException.class;
	}

	public QueryProblem handle(RecognitionException cause) {
		UnwantedTokenException ex = (UnwantedTokenException) cause;
		DefaultQueryProblem qp = new DefaultQueryProblem(ex);
		String msg = String.format(Messages.UNWANTED_TOKEN, ruleName,
				selectExpected(ex.expecting), Messages
						.getTokenErrorDisplay(ex.token));
		qp.setMessage(msg);
		return qp;
	}

	protected abstract String selectExpected(int expecting);

}
