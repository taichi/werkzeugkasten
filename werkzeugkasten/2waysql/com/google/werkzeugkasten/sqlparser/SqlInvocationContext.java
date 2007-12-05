package com.google.werkzeugkasten.sqlparser;

public interface SqlInvocationContext extends SqlContext {

	void bind(Object object);
}
