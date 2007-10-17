package com.google.werkzeugkasten.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface Initializable {

	void initialize();

	@Retention(RetentionPolicy.CLASS)
	@Target(ElementType.METHOD)
	public @interface Initialize {
	}
}
