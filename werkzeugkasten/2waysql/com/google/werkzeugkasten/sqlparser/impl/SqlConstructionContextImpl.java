package com.google.werkzeugkasten.sqlparser.impl;

import java.util.ServiceLoader;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.TokenKind;
import com.google.werkzeugkasten.sqlparser.expression.Expression;
import com.google.werkzeugkasten.sqlparser.expression.ExpressionRegistry;

public class SqlConstructionContextImpl extends
		AbstractSqlContext<SqlConstructionContext> implements
		SqlConstructionContext {

	protected static ServiceLoader<ExpressionRegistry> loader = ServiceLoader
			.load(ExpressionRegistry.class);

	protected static ExpressionRegistry registry;
	static {
		registry = loader.iterator().next();
		registry.initialize();
	}

	protected StringBuilder buffer = new StringBuilder(1024);

	public SqlConstructionContextImpl(char[] fulltext, TokenKind[] tokens) {
		super(fulltext, tokens);
	}

	public StringBuilder getBuffer() {
		return this.buffer;
	}

	public Status execute() {
		return Status.Success;
	}

	public boolean execute(String name, String expression) {
		Expression exp = registry.find(name);
		if (exp != null) {
			return exp.construct(expression, this);
		}
		return false;
	}
}
