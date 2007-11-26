package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.TokenKind;

public class SqlTokenizeContextImplForUnitTest extends SqlTokenizeContextImpl {

	public SqlTokenizeContextImplForUnitTest(String fulltext) {
		super(fulltext);
	}

	SqlTokenizeContextImplForUnitTest() {
		super("");
	}

	public Status execute() {
		return Status.Success;
	}

	public void setTokens(TokenKind[] tokens) {
		this.tokens = tokens;
	}
}
