package com.google.werkzeugkasten.sqlparser.expression.impl;

import java.util.List;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlInvocationContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.TokenLeaf;
import com.google.werkzeugkasten.sqlparser.TokenNode;
import com.google.werkzeugkasten.sqlparser.expression.ScriptEvaluator;
import com.google.werkzeugkasten.util.ClassUtil;

public class WithExpression extends DefaultExpression {

	public WithExpression(ScriptEvaluator evaluator) {
		super(evaluator);
	}

	public boolean construct(String expression, SqlConstructionContext context) {
		String current = context.getBuffer().toString();
		context.getBuffer().setLength(0);
		List<Status> tmp = context.getStatusCopy();
		List<Status> copy = null;
		try {
			context.resetStatus();
			context.setSiblings(context.getChildren());
			for (TokenLeaf t : context.getChildren()) {
				TokenNode node = ClassUtil.as(TokenNode.class, t);
				if (node != null) {
					context.setChildren(node.getChildren());
				}
				t.execute(context);
			}
			copy = context.getStatusCopy();
			if (copy.contains(Status.Success)) {
				context.getBuffer().insert(0, current);
			} else {
				context.getBuffer().setLength(0);
				context.getBuffer().append(current);
			}
		} finally {
			if (copy != null) {
				tmp.addAll(copy);
			}
			context.setStatus(tmp);
		}
		return false;
	}

	public boolean invoke(String expression, SqlInvocationContext context) {
		return true;
	}
}
