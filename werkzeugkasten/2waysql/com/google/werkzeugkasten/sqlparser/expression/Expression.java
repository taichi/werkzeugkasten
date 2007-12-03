package com.google.werkzeugkasten.sqlparser.expression;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlInvocationContext;

public interface Expression<C extends SqlConstructionContext, I extends SqlInvocationContext> {

	void construct(String expression, C context);

	void invoke(String expression, I context);
}
