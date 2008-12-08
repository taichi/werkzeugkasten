package werkzeugkasten.twowaysql.error;

public interface QueryProblem {

	int getLine();

	int getCharPositionInLine();

	String getMessage();

	Exception getCause();
}
