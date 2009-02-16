package werkzeugkasten.twowaysql.jdbc;

import static werkzeugkasten.twowaysql.Markers.DETAIL;
import static werkzeugkasten.twowaysql.nls.Messages.JDBC_CONNECTION_CLOSE;
import static werkzeugkasten.twowaysql.nls.Messages.JDBC_CONNECTION_GET;
import static werkzeugkasten.twowaysql.nls.Messages.JDBC_CONNECTION_HANDLE;
import static werkzeugkasten.twowaysql.nls.Messages.JDBC_RESULTSET_CLOSE;
import static werkzeugkasten.twowaysql.nls.Messages.JDBC_RESULTSET_EXECUTE;
import static werkzeugkasten.twowaysql.nls.Messages.JDBC_RESULTSET_HANDLE;
import static werkzeugkasten.twowaysql.nls.Messages.JDBC_STATEMENT_CLOSE;
import static werkzeugkasten.twowaysql.nls.Messages.JDBC_STATEMENT_HANDLE;
import static werkzeugkasten.twowaysql.nls.Messages.JDBC_STATEMENT_PREPARE;

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
			if (LOG.isDebugEnabled()) {
				LOG.debug(DETAIL, JDBC_CONNECTION_GET);
			}
			con = handle.getConnection();

			if (LOG.isDebugEnabled()) {
				LOG.debug(DETAIL, JDBC_CONNECTION_HANDLE);
			}
			return handle.handle(con);
		} catch (SQLRuntimeException e) {
			throw e;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (LOG.isDebugEnabled()) {
				LOG.debug(DETAIL, JDBC_CONNECTION_CLOSE);
			}
			close(con);
		}
	}

	public static void close(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {
			LOG.error(DETAIL, e.getMessage(), e);
		}
	}

	public static <STATEMENT extends Statement, RESULT> RESULT handleStatement(
			StatementHandler<STATEMENT, RESULT> handler)
			throws SQLRuntimeException {
		STATEMENT statement = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(DETAIL, JDBC_STATEMENT_PREPARE);
			}
			statement = handler.prepare();

			if (LOG.isDebugEnabled()) {
				LOG.debug(DETAIL, JDBC_STATEMENT_HANDLE);
			}
			return handler.handle(statement);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			if (LOG.isDebugEnabled()) {
				LOG.debug(DETAIL, JDBC_STATEMENT_CLOSE);
			}
			close(statement);
		}
	}

	public static void close(Statement s) {
		try {
			if (s != null) {
				s.close();
			}
		} catch (SQLException e) {
			LOG.error(DETAIL, e.getMessage(), e);
		}
	}

	public static <RESULT> RESULT handleResultSet(
			ResultSetHandler<RESULT> handler) throws SQLRuntimeException {
		ResultSet rs = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(DETAIL, JDBC_RESULTSET_EXECUTE);
			}
			rs = handler.executeQuery();

			if (LOG.isDebugEnabled()) {
				LOG.debug(DETAIL, JDBC_RESULTSET_HANDLE);
			}
			return handler.handle(rs);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			if (LOG.isDebugEnabled()) {
				LOG.debug(DETAIL, JDBC_RESULTSET_CLOSE);
			}
			close(rs);
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			LOG.error(DETAIL, e.getMessage(), e);
		}
	}
}
