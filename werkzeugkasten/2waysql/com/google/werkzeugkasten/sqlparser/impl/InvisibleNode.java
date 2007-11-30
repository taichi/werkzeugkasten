package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class InvisibleNode extends AbstractTokenNode {

	public InvisibleNode() {
		super(0);
	}

	public Status execute(SqlConstructionContext parameter) {
		executeChildren(parameter);
		return parameter.execute();
	}

}
