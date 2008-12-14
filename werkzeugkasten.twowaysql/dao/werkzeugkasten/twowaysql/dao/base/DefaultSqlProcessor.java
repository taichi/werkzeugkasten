package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.QueryLoader;
import werkzeugkasten.twowaysql.dao.QueryWrapper;
import werkzeugkasten.twowaysql.dao.ResultSetMapper;
import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.dao.SqlEnviroment;
import werkzeugkasten.twowaysql.dao.SqlExecutor;
import werkzeugkasten.twowaysql.dao.SqlProcessor;
import werkzeugkasten.twowaysql.error.QueryProblemException;
import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class DefaultSqlProcessor implements SqlProcessor {

	protected SqlEnviroment enviroment;

	public void setEnviroment(SqlEnviroment env) {
		this.enviroment = env;
	}

	@Override
	public <LC, EC> Integer process(LC loadingContext, EC expressionContext)
			throws QueryProblemException, SQLRuntimeException {
		SqlContext<EC> context = setUpContext(loadingContext, expressionContext);
		SqlExecutor exec = this.enviroment.getExecutor();
		return exec.execute(context);
	}

	protected <EC, LC> SqlContext<EC> setUpContext(LC loadingContext,
			EC expressionContext) {
		QueryLoader<LC> loader = this.enviroment.getLoader(loadingContext);
		QueryWrapper query = loader.load(loadingContext);
		SqlContext<EC> context = this.enviroment.createContext(
				expressionContext, query);
		QueryTreeVisitor<SqlContext<EC>> visitor = this.enviroment
				.createVisitor();
		query.accept(visitor, context);
		return context;
	}

	@Override
	public <LC, EC, R> R process(LC loadingContext, EC expressionContext,
			ResultSetMapper<R> rsm) throws QueryProblemException,
			SQLRuntimeException {
		SqlContext<EC> context = setUpContext(loadingContext, expressionContext);
		SqlExecutor exec = this.enviroment.getExecutor();
		return exec.execute(context, rsm);
	}

}
