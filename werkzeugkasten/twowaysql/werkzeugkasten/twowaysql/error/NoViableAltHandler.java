package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;

public class NoViableAltHandler implements RecognitionExceptionHandler {

	protected String rulename;

	protected String viable;

	public NoViableAltHandler(String rulename, String viable) {
		this.rulename = rulename;
		this.viable = viable;
	}

	public Class<? extends RecognitionException> getHadleType() {
		return NoViableAltException.class;
	}

	public QueryProblem handle(final RecognitionException ex) {
		DefaultQueryProblem qp = new DefaultQueryProblem(ex);
		String msg = String.format(Messages.NO_VIABLE_ALT, qp.getLine(), qp
				.getCharPositionInLine(), rulename, this.viable, Messages
				.getTokenErrorDisplay(ex.token));
		qp.setMessage(msg);
		return qp;
	}
}