package werkzeugkasten.twowaysql.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcFunctors {

	static final Logger LOG = LoggerFactory.getLogger(JdbcFunctors.class);

	public static <RESULT> RESULT handleConnection(
			ConnectionHandler<RESULT> handle) throws SQLRuntimeException {
		Connection con = null;
		try {
			con = handle.getConnection();
			return handle.handle(con);
		} catch (SQLRuntimeException e) {
			throw e;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(con);
		}
	}

	public static void close(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static <STATEMENT extends Statement, RESULT> RESULT handleStatement(
			StatementHandler<STATEMENT, RESULT> handler)
			throws SQLRuntimeException {
		STATEMENT statement = null;
		try {
			statement = handler.prepare();
			return handler.handle(statement);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(statement);
		}
	}

	public static void close(Statement s) {
		try {
			if (s != null) {
				s.close();
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static <RESULT> RESULT handleResultSet(
			ResultSetHandler<RESULT> handler) throws SQLRuntimeException {
		ResultSet rs = null;
		try {
			rs = handler.executeQuery();
			return handler.handle(rs);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(rs);
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
