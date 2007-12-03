package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.sqlparser.TokenLeaf;

public abstract class AbstractToken implements TokenLeaf {
	protected int offset;

	protected int length;

	public AbstractToken(int offset) {
		setOffset(offset);
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

	public String toString() {
		return String.format("offset [%1$d] length[%2$d]", getOffset(),
				getLength());
	}
}
