package werkzeugkasten.twowaysql.grammar;

public interface QueryProblem {

	int getLine();

	int getCharPositionInLine();

	String getMessage();

	Exception getCause();
}
