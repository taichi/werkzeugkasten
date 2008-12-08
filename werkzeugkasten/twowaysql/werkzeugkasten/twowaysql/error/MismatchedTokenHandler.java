package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.RecognitionException;

public abstract class MismatchedTokenHandler implements
		RecognitionExceptionHandler {

	public MismatchedTokenHandler() {
	}

	public Class<? extends RecognitionException> getHadleType() {
		return MismatchedTokenException.class;
	}

	public QueryProblem handle(RecognitionException cause) {
		MismatchedTokenException ex = (MismatchedTokenException) cause;
		String msg = String.format(Messages.MISMATCHED_TOKEN,
				selectExpected(ex.expecting), Messages
						.getTokenErrorDisplay(ex.token));
		DefaultQueryProblem qp = new DefaultQueryProblem(ex);
		qp.setMessage(msg);
		return qp;
	}

	protected abstract String selectExpected(int expecting);

}
