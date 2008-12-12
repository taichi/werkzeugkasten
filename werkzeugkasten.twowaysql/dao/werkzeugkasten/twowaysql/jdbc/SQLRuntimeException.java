package werkzeugkasten.twowaysql.jdbc;

import java.sql.SQLException;

public class SQLRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4102973235001065627L;

	public SQLRuntimeException(SQLException cause) {
		super(cause);
	}
}
