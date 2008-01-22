package com.google.werkzeugkasten.meta.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.werkzeugkasten.meta.Container;
import com.google.werkzeugkasten.meta.Disposable;
import com.google.werkzeugkasten.meta.Initializable;

public class CompositeContainer implements Container, Initializable, Disposable {

	protected List<AbstractContainer> containers = new ArrayList<AbstractContainer>();

	public void initialize() {
		for (AbstractContainer c : this.containers) {
			c.initialize();
		}
	}

	public void dispose() {
		for (AbstractContainer c : this.containers) {
			c.dispose();
		}
	}

	public void add(AbstractContainer container) {
		this.containers.add(container);
	}

	public <T> T get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> void put(Object key, T component) {
		// TODO Auto-generated method stub

	}
}
