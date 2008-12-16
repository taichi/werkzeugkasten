/**
 * 
 */
package werkzeugkasten.twowaysql.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionHandler<RESULT> {
	Connection getConnection() throws SQLException;

	RESULT handle(Connection c) throws SQLException;
}