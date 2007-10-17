package com.google.werkzeugkasten.meta.impl;

import java.util.HashMap;
import java.util.Map;

import com.google.werkzeugkasten.meta.Container;
import com.google.werkzeugkasten.meta.Disposable;
import com.google.werkzeugkasten.meta.Initializable;

@SuppressWarnings("unchecked")
public abstract class AbstractContainer implements Container, Initializable,
		Disposable {

	protected Map components = new HashMap();

	public <T> T get(Object key) {
		return (T) this.components.get(key);
	}

	public <T> void put(Object key, T component) {
		this.components.put(key, component);
	}
}
