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
		return String.format("%1$s: offset [%2$d] length[%3$d]", getClass()
				.getSimpleName(), getOffset(), getLength());
	}
}
