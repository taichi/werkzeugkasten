package com.google.werkzeugkasten.core.dao.jdbc;

import java.sql.Statement;

import com.google.werkzeugkasten.meta.Chain;

public abstract class BindChain<R, STMT extends Statement, CTX extends JdbcDaoContext<R, STMT, CTX>>
		implements Chain<R, CTX> {

	public R execute(CTX context) {
		validate(context);
		bind(context);
		return context.execute();
	}

	protected void validate(CTX context) {
		STMT s = context.getStatement();
		if (s == null) {
			// FIXME : error msgs
			throw new IllegalStateException("statement is null");
		}
	}

	protected abstract void bind(CTX context);
}
