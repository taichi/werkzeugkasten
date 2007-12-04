package com.google.werkzeugkasten.sqlparser.expression.js;

import java.util.HashMap;
import java.util.Map;

import com.google.werkzeugkasten.sqlparser.expression.Expression;
import com.google.werkzeugkasten.sqlparser.expression.ExpressionRegistry;
import com.google.werkzeugkasten.util.StringUtil;

public class ScriptExpressionRegistry implements ExpressionRegistry {

	protected Map<String, Expression> expressions = new HashMap<String, Expression>();

	public ScriptExpressionRegistry() {
		expressions.put("if", new IfExpression());
		expressions.put("bind", new BindExpression());
		expressions.put("with", new WithExpression());
	}

	public Expression find(String name) {
		if (StringUtil.isEmpty(name) == false) {
			return expressions.get(name.toLowerCase());
		}
		return null;
	}
}
