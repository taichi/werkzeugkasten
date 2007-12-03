package com.google.werkzeugkasten.sqlparser.expression;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlInvocationContext;

public interface ExpressionRegistry<C extends SqlConstructionContext, I extends SqlInvocationContext> {

	void initialize();

	Expression<C, I> find(String name);
}
