package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class SemanticComment extends AbstractToken {

	public SemanticComment(int offset) {
		super(offset);
	}

	public Status execute(SqlConstructionContext parameter) {
		return parameter.execute();
	}

}
