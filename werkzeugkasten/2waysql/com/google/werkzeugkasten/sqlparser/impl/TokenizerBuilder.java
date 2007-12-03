package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.meta.ChainBuilder;
import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class TokenizerBuilder implements
		ChainBuilder<Status, SqlTokenizeContext> {

	protected String twowaysql;

	public TokenizerBuilder(String sql) {
		this.twowaysql = sql;
	}

	public SqlTokenizeContext build() {
		SqlTokenizeContextImpl result = new SqlTokenizeContextImpl(
				this.twowaysql);
		result.add(new Scanner());
		result.add(new Validator());
		result.add(new Tokenizer());
		return result;
	}
}
