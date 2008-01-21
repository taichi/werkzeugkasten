package com.google.werkzeugkasten.core.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface WebController {

	@SuppressWarnings("unchecked")
	Class<? extends WebContext> context();
}
