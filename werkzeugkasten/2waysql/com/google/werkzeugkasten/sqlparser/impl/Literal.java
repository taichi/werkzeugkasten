package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class Literal extends AbstractToken {

	public Literal(int offset) {
		super(offset);
	}

	public Status execute(SqlConstructionContext parameter) {
		// TODO AND ,OR processing ...
		int current = parameter.getCursor();
		int length = getLength() - (parameter.getCursor() - getOffset());
		parameter.getBuffer().append(parameter.getFullText(), current, length);
		parameter.setCursor(current + length);
		return Status.Success;
	}
}
