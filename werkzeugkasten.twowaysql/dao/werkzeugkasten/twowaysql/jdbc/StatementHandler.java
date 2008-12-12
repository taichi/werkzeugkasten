/**
 * 
 */
package werkzeugkasten.twowaysql.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementHandler<S extends Statement, R> {
	S prepare() throws SQLException;

	R handle(S statement) throws SQLException;
}