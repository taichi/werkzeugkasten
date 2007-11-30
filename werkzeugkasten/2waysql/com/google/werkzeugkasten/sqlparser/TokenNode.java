package com.google.werkzeugkasten.sqlparser;

public interface TokenNode extends TokenLeaf {

	void addChild(TokenLeaf token);
}
