package werkzeugkasten.twowaysql.grammar;

import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;

public class EndCommentExceptionMapper extends AbstractExceptionMapper {

	public EndCommentExceptionMapper() {
		add(new NoViableAltHandler());
	}

	public class NoViableAltHandler implements Handler {
		public Class<? extends RecognitionException> getHadleType() {
			return NoViableAltException.class;
		}

		public QueryProblem handle(RecognitionException ex) {
			return null;
		}
	}
}
