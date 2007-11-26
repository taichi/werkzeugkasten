package com.google.werkzeugkasten.sqlparser.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.TokenKind;

public class SqlTokenizeContextImpl extends
		AbstractSqlParserContext<SqlTokenizeContext> implements
		SqlTokenizeContext {

	protected List<TokenKind> tokens;

	public SqlTokenizeContextImpl(String fulltext) {
		super(fulltext);
		tokens = new ArrayList<TokenKind>(fulltext.length());
	}

	public List<TokenKind> getTokens() {
		return this.tokens;
	}

	public void setToken(int index, TokenKind kind) {
		this.tokens.set(index, kind);
	}

}
