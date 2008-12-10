package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.RecognitionException;

public class DefaultQueryProblem extends RecognitionExceptionAdapter {

	protected String msg;

	public DefaultQueryProblem(RecognitionException cause) {
		super(cause);
	}

	public void setMessage(String message) {
		this.msg = message;
	}

	public String getMessage() {
		return this.msg;
	}
}
