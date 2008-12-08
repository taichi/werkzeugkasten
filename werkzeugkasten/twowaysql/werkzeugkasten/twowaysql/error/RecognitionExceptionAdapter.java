package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.RecognitionException;

public class RecognitionExceptionAdapter implements QueryProblem {

	protected RecognitionException cause;

	public RecognitionExceptionAdapter(RecognitionException cause) {
		this.cause = cause;
	}

	public int getLine() {
		return this.cause.line;
	}

	public int getCharPositionInLine() {
		return this.cause.charPositionInLine;
	}

	public String getMessage() {
		return this.cause.getMessage();
	}

	public Exception getCause() {
		return this.cause;
	}
}
