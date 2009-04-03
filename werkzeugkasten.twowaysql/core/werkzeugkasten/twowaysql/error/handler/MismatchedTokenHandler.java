package werkzeugkasten.twowaysql.error.handler;

import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.RecognitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.twowaysql.error.DefaultQueryProblem;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.error.RecognitionExceptionHandler;
import werkzeugkasten.twowaysql.grammar.GrammarUtil;
import werkzeugkasten.twowaysql.nls.Messages;

public class MismatchedTokenHandler implements RecognitionExceptionHandler {

	static final Logger LOG = LoggerFactory
			.getLogger(MismatchedTokenHandler.class);

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
		try {
			String msg = String.format(Messages.MISMATCHED_TOKEN, ruleName,
					GrammarUtil.to(ex.expecting), Messages
							.getTokenErrorDisplay(ex.token));
			qp.setMessage(msg);
		} catch (IllegalStateException e) {
			LOG.error("", e);
		}
		return qp;
	}

}
