package werkzeugkasten.twowaysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<RESULT> {

	ResultSet executeQuery() throws SQLException;

	RESULT handle(ResultSet rs) throws SQLException;
}
