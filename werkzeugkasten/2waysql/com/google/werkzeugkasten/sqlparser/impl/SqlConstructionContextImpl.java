package com.google.werkzeugkasten.sqlparser.impl;

import java.util.ServiceLoader;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.TokenKind;
import com.google.werkzeugkasten.sqlparser.expression.Expression;
import com.google.werkzeugkasten.sqlparser.expression.ExpressionRegistry;

@SuppressWarnings("unchecked")
public class SqlConstructionContextImpl extends
		AbstractSqlContext<SqlConstructionContext> implements
		SqlConstructionContext {

	protected static ServiceLoader<ExpressionRegistry> loader = ServiceLoader
			.load(ExpressionRegistry.class);

	protected static ExpressionRegistry registry;
	static {
		registry = loader.iterator().next();
	}

	protected StringBuilder buffer = new StringBuilder(1024);

	public SqlConstructionContextImpl(char[] fulltext, TokenKind[] tokens) {
		super(fulltext, tokens);
	}

	public StringBuilder getBuffer() {
		return this.buffer;
	}

	public boolean execute(String name, String expression) {
		Expression exp = registry.find(name);
		return exp.construct(expression, this);
	}
}
