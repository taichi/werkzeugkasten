package com.google.werkzeugkasten.sqlparser;

import com.google.werkzeugkasten.meta.Chain;

public interface Token extends Chain<Status, SqlParserContext> {

	int getOffset();

	void setOffset(int offset);

	int getLength();

	void setLength(int length);

}
