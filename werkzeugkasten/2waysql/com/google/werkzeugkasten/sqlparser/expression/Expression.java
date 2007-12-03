package com.google.werkzeugkasten.sqlparser.expression;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlInvocationContext;

public interface Expression {

	boolean construct(String expression, SqlConstructionContext context);

	boolean invoke(String expression, SqlInvocationContext context);
}
