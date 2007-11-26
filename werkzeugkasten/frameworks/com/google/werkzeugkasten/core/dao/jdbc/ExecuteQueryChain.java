package com.google.werkzeugkasten.core.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.werkzeugkasten.meta.Chain;

public class ExecuteQueryChain<R, CTX extends JdbcDaoContext<R, PreparedStatement, CTX>>
		implements Chain<R, CTX> {

	public R execute(CTX context) {
		try {
			validate(context);
			PreparedStatement stmt = context.getStatement();
			context.setResultSet(stmt.executeQuery());
			return context.execute();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	protected void validate(CTX context) {
		PreparedStatement s = context.getStatement();
		if (s == null) {
			// FIXME : error msgs
			throw new IllegalStateException("statement is null");
		}
	}
}
