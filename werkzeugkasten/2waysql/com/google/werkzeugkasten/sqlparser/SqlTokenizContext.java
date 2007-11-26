package com.google.werkzeugkasten.sqlparser;

import java.util.List;

public interface SqlTokenizContext extends SqlParserContext {

	List<TokenKind> getTokens();

	void setToken(int index, TokenKind kind);
}
