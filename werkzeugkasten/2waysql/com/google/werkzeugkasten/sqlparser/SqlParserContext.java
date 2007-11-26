package com.google.werkzeugkasten.sqlparser;

import com.google.werkzeugkasten.meta.ChainContext;

public interface SqlParserContext extends ChainContext<Status> {

	char[] getFullText();

	int getCursor();

	void setCursor(int index);

	StringBuilder getBuffer();
}
