package com.google.werkzeugkasten.sqlparser.expression.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.expression.ScriptEvaluator;

public class BindExpression extends DefaultExpression {

	public BindExpression(ScriptEvaluator evaluator) {
		super(evaluator);
	}

	public boolean construct(String expression, SqlConstructionContext context) {
		context.getBuffer().append("?");
		return false;
	}
}
