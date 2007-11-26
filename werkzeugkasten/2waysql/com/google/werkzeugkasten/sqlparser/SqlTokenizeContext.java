package com.google.werkzeugkasten.sqlparser;

import java.util.List;

public interface SqlTokenizeContext extends SqlParserContext {

	List<TokenKind> getTokens();

	void setToken(int index, TokenKind kind);
}
