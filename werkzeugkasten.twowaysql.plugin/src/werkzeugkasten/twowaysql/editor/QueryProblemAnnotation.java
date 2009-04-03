package werkzeugkasten.twowaysql.editor;

import org.eclipse.jface.text.source.Annotation;

import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.error.QueryProblem;

public class QueryProblemAnnotation extends Annotation {

	protected QueryProblem problem;

	public QueryProblemAnnotation(QueryProblem problem) {
		this.problem = problem;
		setType(Constants.ANNOTATION_TYPE_ERROR);
		setText(problem.getMessage());
	}

	public QueryProblem getProblem() {
		return this.problem;
	}
}
