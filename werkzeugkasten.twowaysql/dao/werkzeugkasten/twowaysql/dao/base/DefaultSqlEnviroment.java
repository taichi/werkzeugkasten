package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.BinderFactory;
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
	protected BinderFactory binderFactory;
	protected SqlExecutor executor;

	@Override
	@SuppressWarnings("unchecked")
	public <LC> QueryLoader<LC> getLoader(LC context) {
		return (QueryLoader<LC>) this.queryLoader;
	}

	@Override
	public <EC> SqlContext<EC> createContext(EC expressionContext,
			QueryWrapper twoWayQuery) {
		return new DefaultSqlContext<EC>(expressionContext, twoWayQuery);
	}

	protected ExpressionParser getELParser() {
		return this.elparser;
	}

	public void setELParser(ExpressionParser elparser) {
		this.elparser = elparser;
	}

	@Override
	public BinderFactory getBinderFactory() {
		return this.binderFactory;
	}

	public void setBinderFactory(BinderFactory factory) {
		this.binderFactory = factory;
	}

	@Override
	public <EC> QueryTreeVisitor<SqlContext<EC>> createVisitor() {
		return new SqlTreeVisitor<EC>(getELParser(), getBinderFactory());
	}

	@Override
	public SqlExecutor getExecutor() {
		return this.executor;
	}

	public void setExecutor(SqlExecutor executor) {
		this.executor = executor;
	}
}
