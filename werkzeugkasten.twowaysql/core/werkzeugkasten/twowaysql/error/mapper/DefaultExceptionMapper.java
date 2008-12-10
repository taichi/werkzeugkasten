package werkzeugkasten.twowaysql.error.mapper;

import org.antlr.runtime.RecognitionException;

import werkzeugkasten.twowaysql.error.ExceptionMapper;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.error.RecognitionExceptionAdapter;

public class DefaultExceptionMapper implements ExceptionMapper {

	public QueryProblem map(RecognitionException ex) {
		return new RecognitionExceptionAdapter(ex);
	}

}
