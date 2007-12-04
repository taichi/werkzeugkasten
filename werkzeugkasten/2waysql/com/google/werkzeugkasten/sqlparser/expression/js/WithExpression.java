package com.google.werkzeugkasten.sqlparser.expression.js;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;

public class WithExpression extends DefaultJsExpression {

	public boolean construct(String expression, SqlConstructionContext context) {
		String tmp = context.getBuffer().toString();

		return true;
	}
}
