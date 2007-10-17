package com.google.werkzeugkasten.meta.impl;

import java.util.ArrayList;
import java.util.List;

public class CompositeContainer extends AbstractContainer {

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
}
