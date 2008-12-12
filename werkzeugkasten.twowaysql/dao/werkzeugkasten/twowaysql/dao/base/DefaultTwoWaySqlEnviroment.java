package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.TwoWayQueryLoader;
import werkzeugkasten.twowaysql.dao.TwoWayQueryWrapper;
import werkzeugkasten.twowaysql.dao.TwoWaySqlContext;
import werkzeugkasten.twowaysql.dao.TwoWaySqlEnviroment;
import werkzeugkasten.twowaysql.dao.TwoWaySqlExecutor;
import werkzeugkasten.twowaysql.dao.el.ExpressionParser;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class DefaultTwoWaySqlEnviroment implements TwoWaySqlEnviroment {

	protected TwoWayQueryLoader<?> queryLoader = new DefaultTwoWayQueryLoader();
	protected ExpressionParser elparser;
	protected TwoWaySqlExecutor executor;

	@Override
	public <EC> TwoWaySqlContext<EC> createContext(EC expressionContext,
			TwoWayQueryWrapper twoWayQuery) {
		return new DefaultTwoWaySqlContext<EC>(expressionContext, twoWayQuery);
	}

	@Override
	public <EC> QueryTreeVisitor<TwoWaySqlContext<EC>> createVisitor(
			ExpressionParser parser) {
		return new TwoWaySqlTreeVisitor<EC>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <LC> TwoWayQueryLoader<LC> getLoader(LC context) {
		return (TwoWayQueryLoader<LC>) this.queryLoader;
	}

	@Override
	public ExpressionParser getELParser() {
		return this.elparser;
	}

	public void setELParser(ExpressionParser elparser) {
		this.elparser = elparser;
	}

	@Override
	public TwoWaySqlExecutor getExecutor() {
		return this.executor;
	}

	public void setExecutor(TwoWaySqlExecutor executor) {
		this.executor = executor;
	}
}
