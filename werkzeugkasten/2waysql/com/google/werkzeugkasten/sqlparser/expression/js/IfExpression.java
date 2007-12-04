package com.google.werkzeugkasten.sqlparser.expression.js;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.util.ClassUtil;

public class IfExpression extends DefaultJsExpression {

	public boolean construct(String expression, SqlConstructionContext context) {
		Boolean is = ClassUtil.as(Boolean.class, eval(expression, context));
		if (is != null && is) {

			context.addStatus(Status.Success);
			return true;
		}
		context.addStatus(Status.Fail);
		return false;
	}

}
