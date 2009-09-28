package org.handwerkszeug.container.event;

public class DefaultEvent implements Event {

	protected Context context;
	protected String name;

	public DefaultEvent(String name, Context context) {
		super();
		this.name = name;
		this.context = context;
	}

	@Override
	public Context context() {
		return this.context;
	}

	@Override
	public String name() {
		return this.name;
	}

}
