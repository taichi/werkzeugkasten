package com.google.werkzeugkasten.sqlparser.expression.js;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;

public class BindExpression extends DefaultJsExpression {

	public boolean construct(String expression, SqlConstructionContext context) {
		context.getBuffer().append("?");
		return false;
	}
}
