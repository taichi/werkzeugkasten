/**
 * 
 */
package werkzeugkasten.twowaysql.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionHandler<R> {
	Connection getConnection() throws SQLException;

	R handle(Connection c) throws SQLException;
}