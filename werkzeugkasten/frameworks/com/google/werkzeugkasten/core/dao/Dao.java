package com.google.werkzeugkasten.core.dao;

public @interface Dao {

	@SuppressWarnings("unchecked")
	Class<? extends DaoContext> context();
}
