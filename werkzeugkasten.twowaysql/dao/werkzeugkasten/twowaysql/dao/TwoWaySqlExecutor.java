package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;

public interface TwoWaySqlExecutor {

	<EC, C extends TwoWaySqlContext<EC>> Integer execute(C context)
			throws SQLRuntimeException;

	<EC, C extends TwoWaySqlContext<EC>, R> R execute(C context,
			ResultSetMapper<R> rsm) throws SQLRuntimeException;

}
