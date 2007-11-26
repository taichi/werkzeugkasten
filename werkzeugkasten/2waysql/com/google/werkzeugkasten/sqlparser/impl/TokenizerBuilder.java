package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.meta.ChainBuilder;
import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class TokenizerBuilder implements
		ChainBuilder<Status, SqlTokenizeContext> {

	protected String sql;

	public TokenizerBuilder(String sql) {
		this.sql = sql;
	}

	public SqlTokenizeContext build() {
		SqlTokenizeContextImpl result = new SqlTokenizeContextImpl(this.sql);
		result.add(new Scanner());
		result.add(new Verifier());
		result.add(new Tokenizer());
		return result;
	}
}
