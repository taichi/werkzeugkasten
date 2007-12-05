package com.google.werkzeugkasten.sqlparser.expression.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlInvocationContext;
import com.google.werkzeugkasten.sqlparser.expression.ScriptEvaluator;

public class BindExpression extends DefaultExpression {

	public BindExpression(ScriptEvaluator evaluator) {
		super(evaluator);
	}

	public boolean construct(String expression, SqlConstructionContext context) {
		context.getBuffer().append("?");
		return false;
	}

	public boolean invoke(String expression, SqlInvocationContext context) {
		context.bind(eval(expression, context));
		return false;
	}
}
