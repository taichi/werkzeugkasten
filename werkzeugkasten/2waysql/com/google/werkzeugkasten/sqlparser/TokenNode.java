package com.google.werkzeugkasten.sqlparser;

import java.util.List;

public interface TokenNode extends TokenLeaf {

	void addChild(TokenLeaf token);

	List<TokenLeaf> getChildren();
}
