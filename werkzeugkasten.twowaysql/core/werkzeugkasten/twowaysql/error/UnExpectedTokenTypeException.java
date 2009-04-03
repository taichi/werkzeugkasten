package werkzeugkasten.twowaysql.error;

public class UnExpectedTokenTypeException extends RuntimeException {

	private static final long serialVersionUID = 7027602347610076659L;

	public UnExpectedTokenTypeException(int type) {
		super(String.format("UNKNOWN[%s]", type));
	}
}
