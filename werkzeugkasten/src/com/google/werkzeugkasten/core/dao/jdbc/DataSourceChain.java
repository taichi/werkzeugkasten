package com.google.werkzeugkasten.core.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DataSourceChain<R, STMT extends Statement, CTX extends JdbcDaoContext<R, STMT, CTX>>
		extends ConnectionChain<R, STMT, CTX> {

	private static final String NAME = "java:/comp/env/dataSource";

	@Override
	protected Connection open() throws SQLException {
		try {
			InitialContext ic = new InitialContext();
			javax.sql.DataSource ds = (javax.sql.DataSource) ic
					.lookup(getDataSourceName());
			if (ds == null) {
				// FIXME : error msgs
				throw new IllegalStateException("DataSource is null");
			}
			return ds.getConnection();
		} catch (NamingException e) {
			throw new IllegalStateException(e);
		}
	}

	protected String getDataSourceName() {
		return NAME;
	}

	public @interface DataSource {
		String value() default NAME;
	}
}
