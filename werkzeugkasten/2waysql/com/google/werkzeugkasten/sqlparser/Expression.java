package com.google.werkzeugkasten.sqlparser;

public interface Expression extends Token {

	void addChild(Token token);
}
