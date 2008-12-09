package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.RecognitionException;

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
		String msg = String.format(Messages.MISMATCHED_TOKEN, qp.getLine(), qp
				.getCharPositionInLine(), ruleName,
				selectExpected(ex.expecting), Messages
						.getTokenErrorDisplay(ex.token));
		qp.setMessage(msg);
		return qp;
	}

	protected abstract String selectExpected(int expecting);

}
