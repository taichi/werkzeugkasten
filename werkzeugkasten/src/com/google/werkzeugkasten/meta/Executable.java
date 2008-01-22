package com.google.werkzeugkasten.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface Executable<R, P> {

	R execute(P parameter);

	@Retention(RetentionPolicy.SOURCE)
	@Target(ElementType.METHOD)
	public @interface Execute {
	}
}
