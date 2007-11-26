package com.google.werkzeugkasten.core.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.werkzeugkasten.meta.Chain;
import com.google.werkzeugkasten.util.StringUtil;

public class PrepareStatementChain<R, CTX extends JdbcDaoContext<R, PreparedStatement, CTX>>
		implements Chain<R, CTX> {

	public R execute(CTX context) {
		PreparedStatement stmt = null;
		try {
			validate(context);
			Connection connection = context.getConnection();
			stmt = connection.prepareStatement(context.getQuery());
			context.setStatement(stmt);
			return context.execute();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		} finally {
			close(stmt);
		}
	}

	protected void validate(CTX context) {
		try {
			if (StringUtil.isEmpty(context.getQuery())) {
				// FIXME : error msgs
				throw new IllegalStateException("Query is empty");
			}
			Connection connection = context.getConnection();
			if (connection == null || connection.isClosed()) {
				// FIXME : error msgs
				throw new IllegalStateException("connection cannot use");
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	protected void close(PreparedStatement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
