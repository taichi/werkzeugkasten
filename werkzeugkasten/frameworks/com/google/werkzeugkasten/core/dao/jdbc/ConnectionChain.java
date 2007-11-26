package com.google.werkzeugkasten.core.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.werkzeugkasten.meta.Chain;

public abstract class ConnectionChain<R, STMT extends Statement, CTX extends JdbcDaoContext<R, STMT, CTX>>
		implements Chain<R, CTX> {

	public R execute(CTX context) {
		Connection connection = null;
		try {
			connection = open();
			if (connection == null || connection.isClosed()) {
				// FIXME : error msgs
				throw new IllegalStateException("connection cannot use");
			}
			context.setConnection(connection);
			return context.execute();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		} finally {
			close(connection);
		}
	}

	protected abstract Connection open() throws SQLException;

	protected void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
