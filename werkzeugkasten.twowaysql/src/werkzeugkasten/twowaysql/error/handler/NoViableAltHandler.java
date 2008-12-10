package werkzeugkasten.twowaysql.error.handler;

import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;

import werkzeugkasten.twowaysql.error.DefaultQueryProblem;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.error.RecognitionExceptionHandler;
import werkzeugkasten.twowaysql.nls.Messages;

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
		String msg = String.format(Messages.NO_VIABLE_ALT, rulename,
				this.viable, Messages.getTokenErrorDisplay(ex.token));
		qp.setMessage(msg);
		return qp;
	}
}