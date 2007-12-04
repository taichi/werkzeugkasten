package com.google.werkzeugkasten.sqlparser.expression.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlContext;
import com.google.werkzeugkasten.sqlparser.SqlInvocationContext;
import com.google.werkzeugkasten.sqlparser.expression.Expression;
import com.google.werkzeugkasten.sqlparser.expression.ScriptEvaluator;

public abstract class DefaultExpression implements Expression {

	protected ScriptEvaluator evaluator;

	public DefaultExpression(ScriptEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	protected Object eval(String expression, SqlContext context) {
		return evaluator.eval(expression, context);
	}

	public boolean construct(String expression, SqlConstructionContext context) {
		return false;
	}

	public boolean invoke(String expression, SqlInvocationContext context) {
		return false;
	}
}
