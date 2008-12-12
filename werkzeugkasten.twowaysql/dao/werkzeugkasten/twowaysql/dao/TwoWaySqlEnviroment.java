package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.dao.el.ExpressionParser;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

/**
 * abstract factory of TwoWaySql.
 * 
 * @author taichi
 */
public interface TwoWaySqlEnviroment {

	<LC> TwoWayQueryLoader<LC> getLoader(LC context);

	<EC> TwoWaySqlContext<EC> createContext(EC expressionContext,
			TwoWayQueryWrapper twoWayQuery);

	ExpressionParser getELParser();

	<EC> QueryTreeVisitor<TwoWaySqlContext<EC>> getVisitor(
			ExpressionParser parser);

	TwoWaySqlExecutor getExecutor();

}
