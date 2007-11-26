package com.google.werkzeugkasten.core.map;

public interface MapDescriptor<T> {

	Object get(T target);

	void set(T target, Object value);
}
