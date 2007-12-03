package com.google.werkzeugkasten.sqlparser;

public interface SqlConstructionContext extends SqlContext {

	StringBuilder getBuffer();

	boolean execute(String name, String expression);
}
