package com.google.werkzeugkasten.sqlparser.expression;

import com.google.werkzeugkasten.sqlparser.SqlContext;

public interface ScriptEvaluator {

	Object eval(String expression, SqlContext context);
}
