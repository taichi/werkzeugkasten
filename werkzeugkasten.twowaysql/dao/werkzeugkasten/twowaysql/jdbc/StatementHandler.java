package werkzeugkasten.twowaysql.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementHandler<STATEMENT extends Statement, RESULT> {
	STATEMENT prepare() throws SQLException;

	RESULT handle(STATEMENT statement) throws SQLException;
}