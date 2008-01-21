package com.google.werkzeugkasten.core.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target( { ElementType.TYPE, ElementType.METHOD })
public @interface Scope {

	@SuppressWarnings("unchecked")
	Class<? extends ScopeProvider> value();
}
