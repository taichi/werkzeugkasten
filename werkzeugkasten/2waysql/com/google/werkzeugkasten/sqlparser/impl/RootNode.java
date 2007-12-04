package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.Status.*;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class RootNode extends AbstractTokenNode {

	public RootNode() {
		super(0);
	}

	public Status execute(SqlConstructionContext parameter) {
		executeChildren(parameter);
		return Success;
	}

}
