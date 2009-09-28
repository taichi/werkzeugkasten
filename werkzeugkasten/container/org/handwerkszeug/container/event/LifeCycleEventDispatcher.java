package org.handwerkszeug.container.event;

import java.util.List;
import java.util.Map;

public class LifeCycleEventDispatcher extends TableEventDispatcher {

	public LifeCycleEventDispatcher() {
		super();
	}

	public LifeCycleEventDispatcher(Map<String, List<EventHandler>> table) {
		super(table);
	}

	public void entry(final LifeCycle lifeCycle) {
		entry(LifeCycle.Type.INITIALIZE.name(), new EventHandler() {

			@Override
			public void handle(Context c) {
				lifeCycle.initialize(c);
			}
		});

		entry(LifeCycle.Type.DISPOSE.name(), new EventHandler() {

			@Override
			public void handle(Context c) {
				lifeCycle.dispose(c);
			}
		});
	}
}
