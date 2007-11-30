package com.google.werkzeugkasten.sqlparser;

import com.google.werkzeugkasten.meta.Chain;

public interface TokenLeaf extends Chain<Status, SqlConstructionContext> {

	int getOffset();

	void setOffset(int offset);

	int getLength();

	void setLength(int length);

}
