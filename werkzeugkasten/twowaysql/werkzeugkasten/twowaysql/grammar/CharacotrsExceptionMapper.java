package werkzeugkasten.twowaysql.grammar;

import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.RecognitionException;

public class CharacotrsExceptionMapper extends AbstractExceptionMapper {

	public CharacotrsExceptionMapper() {

		add(new EarlyExitHandler());
	}

	class EarlyExitHandler implements Handler {
		public Class<? extends RecognitionException> getHadleType() {
			return EarlyExitException.class;
		}

		public QueryProblem handle(final RecognitionException ex) {
			final String msg = "required (...)+ loop did not match anything at input ";
			QueryProblem qp = new RecognitionExceptionAdapter(ex) {
				public String getMessage() {
					return msg + getTokenErrorDisplay(ex.token);
				}
			};
			return qp;
		}
	}
}
