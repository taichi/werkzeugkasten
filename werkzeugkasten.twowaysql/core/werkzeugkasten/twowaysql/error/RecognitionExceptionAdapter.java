package werkzeugkasten.twowaysql.error;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class RecognitionExceptionAdapter implements QueryProblem {

	protected RecognitionException cause;

	public RecognitionExceptionAdapter(RecognitionException cause) {
		this.cause = cause;
		// エラーが発生した場所が、分らないとエラー表示しようが無いので、一つ前のを指す。
		if (cause.token != null
				&& (cause.token.getCharPositionInLine() < 0 || cause.token
						.getLine() < 1) && (cause.input instanceof TokenStream)) {
			TokenStream ts = (TokenStream) cause.input;
			Token t = ts.LT(-1);
			if (t != null) {
				this.cause.token = t;
				this.cause.index = this.cause.index - 1;
				this.cause.line = this.cause.token.getLine();
				this.cause.charPositionInLine = this.cause.token
						.getCharPositionInLine();
			}
		}
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

	public RecognitionException getCause() {
		return this.cause;
	}
}
