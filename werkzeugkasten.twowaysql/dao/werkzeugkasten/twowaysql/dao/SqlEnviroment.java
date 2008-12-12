package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.dao.el.ExpressionParser;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

/**
 * abstract factory of TwoWaySql.
 * 
 * @author taichi
 */
public interface SqlEnviroment {

	<LC> QueryLoader<LC> getLoader(LC context);

	<EC> SqlContext<EC> createContext(EC expressionContext,
			QueryWrapper twoWayQuery);

	ExpressionParser getELParser();

	<EC> QueryTreeVisitor<SqlContext<EC>> createVisitor(
			ExpressionParser parser);

	SqlExecutor getExecutor();

}
