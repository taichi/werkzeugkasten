package werkzeugkasten.twowaysql.grammar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.RecognitionException;

public class ProblemCoordinator {

	protected List<QueryProblem> problems = new ArrayList<QueryProblem>();
	protected LinkedList<ExceptionMapper> stack = new LinkedList<ExceptionMapper>();

	protected void push(ExceptionMapper mapper) {
		this.stack.addFirst(mapper);
	}

	protected void pop() {
		this.stack.removeFirst();
	}

	public void report(RecognitionException ex) {
		ExceptionMapper em = stack.getFirst();
		this.problems.add(em.map(ex));
	}

	public Iterable<QueryProblem> getAll() {
		return this.problems;
	}
}
