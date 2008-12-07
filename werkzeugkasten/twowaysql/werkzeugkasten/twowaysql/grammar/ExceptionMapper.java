package werkzeugkasten.twowaysql.grammar;

import org.antlr.runtime.RecognitionException;

public interface ExceptionMapper {

	QueryProblem map(RecognitionException ex);
}
