package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.QueryLoader;
import werkzeugkasten.twowaysql.dao.QueryWrapper;
import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.dao.SqlEnviroment;
import werkzeugkasten.twowaysql.dao.SqlExecutor;
import werkzeugkasten.twowaysql.dao.el.ExpressionParser;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class DefaultSqlEnviroment implements SqlEnviroment {

	protected QueryLoader<?> queryLoader = new DefaultQueryLoader();
	protected ExpressionParser elparser;
	protected SqlExecutor executor;

	@Override
	public <EC> SqlContext<EC> createContext(EC expressionContext,
			QueryWrapper twoWayQuery) {
		return new DefaultSqlContext<EC>(expressionContext, twoWayQuery);
	}

	@Override
	public <EC> QueryTreeVisitor<SqlContext<EC>> createVisitor() {
		return new SqlTreeVisitor<EC>(getELParser());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <LC> QueryLoader<LC> getLoader(LC context) {
		return (QueryLoader<LC>) this.queryLoader;
	}

	protected ExpressionParser getELParser() {
		return this.elparser;
	}

	public void setELParser(ExpressionParser elparser) {
		this.elparser = elparser;
	}

	@Override
	public SqlExecutor getExecutor() {
		return this.executor;
	}

	public void setExecutor(SqlExecutor executor) {
		this.executor = executor;
	}
}
