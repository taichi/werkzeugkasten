package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.ResultSetMapper;
import werkzeugkasten.twowaysql.dao.TwoWayQueryLoader;
import werkzeugkasten.twowaysql.dao.TwoWayQueryWrapper;
import werkzeugkasten.twowaysql.dao.TwoWaySqlContext;
import werkzeugkasten.twowaysql.dao.TwoWaySqlEnviroment;
import werkzeugkasten.twowaysql.dao.TwoWaySqlExecutor;
import werkzeugkasten.twowaysql.dao.TwoWaySqlProcessor;
import werkzeugkasten.twowaysql.dao.el.ExpressionParser;
import werkzeugkasten.twowaysql.error.QueryProblemException;
import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class DefaultTwoWaySqlProcessor implements TwoWaySqlProcessor {

	protected TwoWaySqlEnviroment enviroment;

	public void setEnviroment(TwoWaySqlEnviroment env) {
		this.enviroment = env;
	}

	@Override
	public <LC, EC> Integer process(LC loadingContext, EC expressionContext)
			throws QueryProblemException, SQLRuntimeException {
		TwoWaySqlContext<EC> context = setupContext(loadingContext,
				expressionContext);
		TwoWaySqlExecutor exec = this.enviroment.getExecutor();
		return exec.execute(context);
	}

	protected <EC, LC> TwoWaySqlContext<EC> setupContext(LC loadingContext,
			EC expressionContext) {
		@SuppressWarnings("unchecked")
		Class<LC> clazz = (Class<LC>) loadingContext.getClass();
		TwoWayQueryLoader<LC> loader = this.enviroment.getLoader(clazz);
		TwoWayQueryWrapper query = loader.load(loadingContext);
		TwoWaySqlContext<EC> context = this.enviroment.createContext(
				expressionContext, query);
		ExpressionParser el = this.enviroment.getELParser();
		QueryTreeVisitor<TwoWaySqlContext<EC>> visitor = this.enviroment
				.getVisitor(el);
		query.accept(visitor, context);
		return context;
	}

	@Override
	public <LC, EC, R> R process(LC loadingContext, EC expressionContext,
			ResultSetMapper<R> rsm) throws QueryProblemException,
			SQLRuntimeException {
		TwoWaySqlContext<EC> context = setupContext(loadingContext,
				expressionContext);
		TwoWaySqlExecutor exec = this.enviroment.getExecutor();
		return exec.execute(context, rsm);
	}

}
