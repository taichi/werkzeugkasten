package werkzeugkasten.twowaysql.dao;

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

	<EC> QueryTreeVisitor<SqlContext<EC>> createVisitor();

	SqlExecutor getExecutor();

}
