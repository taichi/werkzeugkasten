package com.google.werkzeugkasten.sqlparser.impl;

import java.util.Set;
import java.util.TreeSet;

import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.TokenKind;

public class SqlTokenizeContextImpl extends
		AbstractSqlParserContext<SqlTokenizeContext> implements
		SqlTokenizeContext {

	protected TokenKind[] tokens;

	protected Set<String> messages = new TreeSet<String>();

	public SqlTokenizeContextImpl(String fulltext) {
		super(fulltext);
		tokens = new TokenKind[fulltext.length()];
	}

	public TokenKind[] getTokens() {
		return this.tokens;
	}

	public void setToken(int index, TokenKind kind) {
		this.tokens[index] = kind;
	}

	public Set<String> getMessages() {
		return this.messages;
	}

	public void addMessage(String msg) {
		messages.add(msg);
	}

}
