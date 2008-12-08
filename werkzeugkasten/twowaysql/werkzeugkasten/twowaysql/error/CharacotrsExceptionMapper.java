package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.RecognitionException;

public class CharacotrsExceptionMapper extends AbstractExceptionMapper {

	public CharacotrsExceptionMapper() {
		add(new EarlyExitHandler());
	}

	class EarlyExitHandler implements RecognitionExceptionHandler {
		public Class<? extends RecognitionException> getHadleType() {
			return EarlyExitException.class;
		}

		public QueryProblem handle(final RecognitionException ex) {
			final String msg = "required (...)+ loop did not match anything at input "
					+ Messages.getTokenErrorDisplay(ex.token);
			DefaultQueryProblem qp = new DefaultQueryProblem(ex);
			qp.setMessage(msg);
			return qp;
		}
	}
}
