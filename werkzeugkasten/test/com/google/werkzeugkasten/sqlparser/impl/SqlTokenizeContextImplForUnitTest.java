package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.Status;

public class SqlTokenizeContextImplForUnitTest extends SqlTokenizeContextImpl {

	public SqlTokenizeContextImplForUnitTest(String fulltext) {
		super(fulltext);
	}

	public Status execute() {
		return Status.Success;
	}
}
