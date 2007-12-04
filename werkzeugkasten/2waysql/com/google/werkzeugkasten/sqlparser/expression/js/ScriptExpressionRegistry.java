package com.google.werkzeugkasten.sqlparser.expression.js;

import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.google.werkzeugkasten.sqlparser.SqlContext;
import com.google.werkzeugkasten.sqlparser.expression.Expression;
import com.google.werkzeugkasten.sqlparser.expression.ExpressionRegistry;
import com.google.werkzeugkasten.sqlparser.expression.ScriptEvaluator;
import com.google.werkzeugkasten.sqlparser.expression.impl.BindExpression;
import com.google.werkzeugkasten.sqlparser.expression.impl.IfExpression;
import com.google.werkzeugkasten.sqlparser.expression.impl.WithExpression;
import com.google.werkzeugkasten.util.StringUtil;

public class ScriptExpressionRegistry implements ExpressionRegistry {

	protected ScriptEvaluator evaluator = new JavaScriptEvaluator();
	protected Map<String, Expression> expressions = new HashMap<String, Expression>();

	public ScriptExpressionRegistry() {
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

	protected class JavaScriptEvaluator implements ScriptEvaluator {
		protected ScriptEngine engine;

		public JavaScriptEvaluator() {
			ScriptEngineManager manager = new ScriptEngineManager();
			this.engine = manager.getEngineByExtension("js");
		}

		public Object eval(String expression, SqlContext context) {
			Bindings bindings = this.engine.createBindings();
			for (String key : context.keys()) {
				bindings.put(key, context.get(key));
			}
			try {
				return this.engine.eval(expression, bindings);
			} catch (ScriptException e) {
				return null;
			}
		}
	}
}
