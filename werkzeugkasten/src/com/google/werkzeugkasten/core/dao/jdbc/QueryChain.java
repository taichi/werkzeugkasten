package com.google.werkzeugkasten.core.dao.jdbc;

import java.sql.Statement;

import com.google.werkzeugkasten.meta.Chain;
import com.google.werkzeugkasten.util.StringUtil;

public abstract class QueryChain<R, STMT extends Statement, CTX extends JdbcDaoContext<R, STMT, CTX>>
		implements Chain<R, CTX> {

	public R execute(CTX context) {
		String s = getQuery(context);
		if (StringUtil.isEmpty(s)) {
			// FIXME error msg
			throw new IllegalStateException("Query is Empty");
		}
		context.setQuery(s);
		return context.execute();
	}

	protected abstract String getQuery(CTX context);
}
