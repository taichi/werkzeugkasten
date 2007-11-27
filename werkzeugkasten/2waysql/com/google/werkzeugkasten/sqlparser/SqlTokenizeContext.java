package com.google.werkzeugkasten.sqlparser;

import java.util.List;

public interface SqlTokenizeContext extends SqlParserContext {

	TokenKind[] getTokens();

	void setToken(int index, TokenKind kind);

	List<String> getMessages();

	void addMessage(String msg);

}
