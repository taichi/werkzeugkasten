package com.google.werkzeugkasten.sqlparser.expression.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlInvocationContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.expression.ScriptEvaluator;
import com.google.werkzeugkasten.util.ClassUtil;

public class IfExpression extends DefaultExpression {

	public IfExpression(ScriptEvaluator evaluator) {
		super(evaluator);
	}

	public boolean construct(String expression, SqlConstructionContext context) {
		Boolean is = ClassUtil.as(Boolean.class, eval(expression, context));
		if (is != null && is) {
			context.addStatus(Status.Success);
			return true;
		}
		context.addStatus(Status.Fail);
		return false;
	}

	public boolean invoke(String expression, SqlInvocationContext context) {
		return true;
	}
}
