package com.google.werkzeugkasten.sqlparser;

import java.util.Deque;
import java.util.Set;

public interface SqlTokenizeContext extends SqlContext {

	TokenKind[] getTokens();

	void setToken(int index, TokenKind kind);

	void beginBrace(int index);

	int endBrace();

	Deque<Integer> getBraces();

	Set<String> getMessages();

	void addMessage(String msg);

	void setRoot(TokenNode token);

	TokenNode getRoot();

}
