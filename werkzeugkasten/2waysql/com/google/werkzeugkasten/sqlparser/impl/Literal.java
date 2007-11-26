package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.SqlParserContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.Token;

public class Literal implements Token {

	protected int offset;

	protected int length;

	public Literal(int offset, int length) {
		setOffset(offset);
		setLength(length);
	}

	@Override
	public Status execute(SqlParserContext parameter) {
		parameter.getBuffer().append(parameter.getFullText(), getOffset(),
				getLength());
		return Status.Success;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
