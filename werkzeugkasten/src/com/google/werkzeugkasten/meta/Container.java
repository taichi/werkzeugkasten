package com.google.werkzeugkasten.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface Container {

	<T> T get(Object key);

	<T> void put(Object key, T component);

	@Retention(RetentionPolicy.SOURCE)
	@Target(ElementType.PARAMETER)
	public @interface Query {
		boolean failOnError() default false;

		QueryType[] value() default { QueryType.Type, QueryType.Name };
	}

	public enum QueryType {
		Name, Type
	}
}
