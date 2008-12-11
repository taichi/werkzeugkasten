package werkzeugkasten.twowaysql.error;

public class QueryProblemException extends RuntimeException {

	private static final long serialVersionUID = 943374637103750249L;

	protected Iterable<QueryProblem> problems;

	public QueryProblemException(Iterable<QueryProblem> problems) {
		super(toMessage(problems));
		this.problems = problems;
	}

	protected static String toMessage(Iterable<QueryProblem> problems) {
		StringBuilder stb = new StringBuilder();
		for (QueryProblem qp : problems) {
			stb.append(String.format("[%d:%d] %s%n", qp.getLine(), qp
					.getCharPositionInLine(), qp.getMessage()));
		}
		return stb.toString();
	}

	public Iterable<QueryProblem> getProblems() {
		return this.problems;
	}
}
