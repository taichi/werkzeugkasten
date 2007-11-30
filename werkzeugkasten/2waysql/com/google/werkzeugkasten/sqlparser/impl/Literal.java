package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class Literal extends AbstractToken {

	public Literal(int offset) {
		super(offset);
	}

	public Status execute(SqlConstructionContext parameter) {
		parameter.getBuffer().append(parameter.getFullText(),
				parameter.getCursor(),
				getLength() - (parameter.getCursor() - getOffset()));
		return Status.Success;
	}
}
