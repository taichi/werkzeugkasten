package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.RecognitionException;

public interface ExceptionMapper {

	QueryProblem map(RecognitionException ex);
}
