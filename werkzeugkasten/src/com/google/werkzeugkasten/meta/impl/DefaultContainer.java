package com.google.werkzeugkasten.meta.impl;


public abstract class DefaultContainer extends AbstractContainer {

	public void initialize() {
		ContainerContext initializer = getInitializer();
		try {
			initializer.initialize(this);
			initializer.execute();
		} finally {
			initializer.dispose();
		}
	}

	public void dispose() {
		ContainerContext disposer = getDisposer();
		try {
			disposer.initialize(this);
			disposer.execute();
		} finally {
			disposer.dispose();
		}
	}

	protected abstract ContainerContext getInitializer();

	protected abstract ContainerContext getDisposer();

}
