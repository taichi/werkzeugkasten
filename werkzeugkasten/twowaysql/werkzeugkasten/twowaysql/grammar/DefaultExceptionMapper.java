package werkzeugkasten.twowaysql.grammar;

import org.antlr.runtime.RecognitionException;

public class DefaultExceptionMapper implements ExceptionMapper {

	public QueryProblem map(RecognitionException ex) {
		return new RecognitionExceptionAdapter(ex);
	}

}
