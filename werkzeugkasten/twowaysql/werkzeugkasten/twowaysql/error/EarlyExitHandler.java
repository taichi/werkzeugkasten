package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.RecognitionException;

import werkzeugkasten.twowaysql.nls.Messages;

public class EarlyExitHandler implements RecognitionExceptionHandler {

	protected String ruleName;
	protected String required;

	public EarlyExitHandler(String ruleName, String required) {
		this.ruleName = ruleName;
		this.required = required;
	}

	public Class<? extends RecognitionException> getHadleType() {
		return EarlyExitException.class;
	}

	public QueryProblem handle(final RecognitionException ex) {
		DefaultQueryProblem qp = new DefaultQueryProblem(ex);
		String msg = String.format(Messages.EARLY_EXIT, ruleName, required,
				Messages.getTokenErrorDisplay(ex.token));
		qp.setMessage(msg);
		return qp;
	}
}