package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.RecognitionException;

public interface QueryProblem {

	int getLine();

	int getCharPositionInLine();

	String getMessage();

	RecognitionException getCause();
}
