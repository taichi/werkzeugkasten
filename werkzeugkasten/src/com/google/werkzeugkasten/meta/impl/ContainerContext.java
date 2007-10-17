/**
 * 
 */
package com.google.werkzeugkasten.meta.impl;

import com.google.werkzeugkasten.meta.Container;
import com.google.werkzeugkasten.meta.Disposable;
import com.google.werkzeugkasten.meta.Initializable.Initialize;

public class ContainerContext extends
		AbstractChainContext<Void, ContainerContext> implements Disposable {

	protected Container container;

	public Container getContainer() {
		return this.container;
	}

	@Initialize
	public void initialize(Container container) {
		this.container = container;
	}

	public void dispose() {
		this.container = null;
	}
}