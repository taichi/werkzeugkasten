/**
 * 
 */
package com.google.werkzeugkasten.sqlparser.expression.js;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.google.werkzeugkasten.sqlparser.SqlContext;
import com.google.werkzeugkasten.sqlparser.expression.ScriptEvaluator;

class JavaScriptEvaluator implements ScriptEvaluator {
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