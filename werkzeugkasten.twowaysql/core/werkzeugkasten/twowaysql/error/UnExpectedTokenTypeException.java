package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.nls.Messages;

public class UnExpectedTokenTypeException extends RuntimeException {

	private static final long serialVersionUID = 7027602347610076659L;

	public UnExpectedTokenTypeException(int type) {
		super(String.format(Messages.UNEXPECTED_TOKEN_TYPE, type));
	}
}
