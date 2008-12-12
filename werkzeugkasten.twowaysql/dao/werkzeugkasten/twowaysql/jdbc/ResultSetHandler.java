package werkzeugkasten.twowaysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<R> {

	ResultSet executeQuery() throws SQLException;

	R handle(ResultSet rs) throws SQLException;
}
