package org.handwerkszeug.container.reflect;

import org.handwerkszeug.container.Nameable;

public abstract class AbstractNameable implements Nameable {

	protected String name;

	protected AbstractNameable(String name) {
		this.name = name;
	}

	public String name() {
		return this.name;
	}

}
