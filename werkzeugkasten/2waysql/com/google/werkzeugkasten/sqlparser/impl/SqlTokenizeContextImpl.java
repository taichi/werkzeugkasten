package com.google.werkzeugkasten.sqlparser.impl;

import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.TokenKind;

public class SqlTokenizeContextImpl extends
		AbstractSqlContext<SqlTokenizeContext> implements SqlTokenizeContext {

	protected Deque<Integer> braces = new LinkedList<Integer>();

	protected Set<String> messages = new LinkedHashSet<String>();

	public SqlTokenizeContextImpl(String fulltext) {
		super(fulltext);
		tokens = new TokenKind[fulltext.length()];
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

	public void beginBrace(int index) {
		braces.addLast(index);
	}

	public int endBrace() {
		Integer result = braces.pollLast();
		return result == null ? -1 : result;
	}

	public Deque<Integer> getBraces() {
		return braces;
	}
}
