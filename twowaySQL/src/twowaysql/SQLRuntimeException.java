package twowaysql;

import java.sql.SQLException;

public class SQLRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -7486695738744905270L;

	public SQLRuntimeException(SQLException cause) {
		super(cause);
	}

}
