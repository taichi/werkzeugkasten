package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;

public class SqlConstructionContextImpl extends
		AbstractSqlContext<SqlConstructionContext> implements
		SqlConstructionContext {

	protected StringBuilder buffer = new StringBuilder(1024);

	public SqlConstructionContextImpl(char[] fulltext) {
		super(fulltext);
	}

	public StringBuilder getBuffer() {
		return this.buffer;
	}

}
