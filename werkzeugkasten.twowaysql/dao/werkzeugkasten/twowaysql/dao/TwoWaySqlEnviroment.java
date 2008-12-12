package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.dao.el.ExpressionParser;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

/**
 * abstract factory of TwoWaySql.
 * 
 * @author taichi
 */
public interface TwoWaySqlEnviroment {

	<LC> TwoWayQueryLoader<LC> createLoader(Class<LC> contextType);

	<EC> TwoWaySqlContext<EC> createContext(EC expressionContext,
			TwoWayQueryWrapper twoWayQuery);

	ExpressionParser createELParser();

	TwoWaySqlExecutor createExecutor();

	<EC, C extends TwoWaySqlContext<EC>> QueryTreeVisitor<C> createVisitor();
}
