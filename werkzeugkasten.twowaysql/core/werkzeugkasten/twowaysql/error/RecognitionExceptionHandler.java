package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.RecognitionException;

public interface RecognitionExceptionHandler {
	Class<? extends RecognitionException> getHadleType();

	QueryProblem handle(RecognitionException ex);
}