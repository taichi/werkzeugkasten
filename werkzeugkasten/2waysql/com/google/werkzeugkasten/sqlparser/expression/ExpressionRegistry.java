package com.google.werkzeugkasten.sqlparser.expression;

public interface ExpressionRegistry {

	Expression find(String name);
}
