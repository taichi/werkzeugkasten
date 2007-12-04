package com.google.werkzeugkasten.sqlparser.expression.js;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlContext;
import com.google.werkzeugkasten.sqlparser.SqlInvocationContext;
import com.google.werkzeugkasten.sqlparser.expression.Expression;

public abstract class DefaultJsExpression implements Expression {

	protected ScriptEngine engine;

	public DefaultJsExpression() {
		ScriptEngineManager manager = new ScriptEngineManager();
		this.engine = manager.getEngineByExtension("js");
	}

	protected Object eval(String expression, SqlContext context) {
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

	public boolean construct(String expression, SqlConstructionContext context) {
		return false;
	}

	public boolean invoke(String expression, SqlInvocationContext context) {
		return false;
	}

}
