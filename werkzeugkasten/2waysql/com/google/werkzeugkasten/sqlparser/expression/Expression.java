package com.google.werkzeugkasten.sqlparser.expression;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;

public interface Expression {

	void execute(String expression, SqlConstructionContext context);
}
