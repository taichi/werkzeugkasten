package org.handwerkszeug.container.event;

import org.handwerkszeug.container.Factory;

public interface LifeCycle {

	void initialize(Context c);

	void dispose(Context c);

	enum Type implements Factory<Event, Context> {
		INITIALIZE {
			@Override
			public Event create(Context c) {
				return new DefaultEvent(LifeCycle.Type.INITIALIZE.getClass()
						.getName(), c);
			}
		},
		DISPOSE {
			@Override
			public Event create(Context c) {
				return new DefaultEvent(LifeCycle.Type.DISPOSE.getClass()
						.getName(), c);
			}
		};
	}
}
