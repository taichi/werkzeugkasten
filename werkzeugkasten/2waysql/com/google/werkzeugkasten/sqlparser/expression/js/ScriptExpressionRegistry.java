package com.google.werkzeugkasten.sqlparser.expression.js;

import java.util.HashMap;
import java.util.Map;

import com.google.werkzeugkasten.sqlparser.expression.Expression;
import com.google.werkzeugkasten.sqlparser.expression.ExpressionRegistry;
import com.google.werkzeugkasten.sqlparser.expression.ScriptEvaluator;
import com.google.werkzeugkasten.sqlparser.expression.impl.BindExpression;
import com.google.werkzeugkasten.sqlparser.expression.impl.IfExpression;
import com.google.werkzeugkasten.sqlparser.expression.impl.WithExpression;
import com.google.werkzeugkasten.util.StringUtil;

public class ScriptExpressionRegistry implements ExpressionRegistry {

	protected Map<String, Expression> expressions;

	public ScriptExpressionRegistry() {
	}

	public void initialize() {
		ScriptEvaluator evaluator = new JavaScriptEvaluator();
		expressions = new HashMap<String, Expression>();
		expressions.put("if", new IfExpression(evaluator));
		expressions.put("bind", new BindExpression(evaluator));
		expressions.put("with", new WithExpression(evaluator));
	}

	public Expression find(String name) {
		if (StringUtil.isEmpty(name) == false) {
			return expressions.get(name.toLowerCase());
		}
		return null;
	}
}
