package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.error.QueryProblemException;

public interface TwoWaySqlProcessor {

	<LC, EC, R> R process(LC loadingContext, EC expressionContext,
			ResultSetHandler<R> rsh) throws QueryProblemException,
			SQLRuntimeException;
}
