package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;

public interface SqlExecutor {

	<EC, C extends SqlContext<EC>> Integer execute(C context)
			throws SQLRuntimeException;

	<EC, C extends SqlContext<EC>, R> R execute(C context,
			ResultSetMapper<R> rsm) throws SQLRuntimeException;

}
