package com.google.werkzeugkasten.sqlparser.expression;

public interface ExpressionRegistry {

	void initialize();

	Expression find(String name);
}
