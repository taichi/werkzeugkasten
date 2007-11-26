package com.google.werkzeugkasten.core.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.werkzeugkasten.core.dao.DaoContext;
import com.google.werkzeugkasten.meta.impl.AbstractChainContext;

public class JdbcDaoContext<R, STMT extends Statement, CTX extends JdbcDaoContext<R, STMT, CTX>>
		extends AbstractChainContext<R, CTX> implements DaoContext<R> {

	protected String query;
	protected Connection connection;
	protected STMT statement;
	protected ResultSet resultSet;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public STMT getStatement() {
		return statement;
	}

	public void setStatement(STMT statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
}