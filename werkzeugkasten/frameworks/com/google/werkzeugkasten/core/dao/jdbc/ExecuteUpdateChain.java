package com.google.werkzeugkasten.core.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.werkzeugkasten.meta.Chain;

public class ExecuteUpdateChain<CTX extends JdbcDaoContext<Integer, PreparedStatement, CTX>>
		implements Chain<Integer, CTX> {

	public Integer execute(CTX context) {
		try {
			validate(context);
			PreparedStatement s = context.getStatement();
			return s.executeUpdate();
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
