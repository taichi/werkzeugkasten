package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class Functor extends AbstractToken {

	protected String name;

	protected String expression;

	public Functor(int offset) {
		super(offset);
	}

	public Status execute(SqlConstructionContext parameter) {
		// TODO Auto-generated method stub
		return parameter.execute();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

}
