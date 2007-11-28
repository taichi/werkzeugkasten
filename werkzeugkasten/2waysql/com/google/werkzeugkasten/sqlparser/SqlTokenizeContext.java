package com.google.werkzeugkasten.sqlparser;

import java.util.Set;

public interface SqlTokenizeContext extends SqlParserContext {

	TokenKind[] getTokens();

	void setToken(int index, TokenKind kind);

	Set<String> getMessages();

	void addMessage(String msg);

}
