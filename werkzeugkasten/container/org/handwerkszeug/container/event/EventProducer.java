package org.handwerkszeug.container.event;

import java.util.ArrayList;
import java.util.List;

public class EventProducer {

	protected List<EventDispatcher> dispatchers = new ArrayList<EventDispatcher>();

	public void produce(Event e) {
		for (EventDispatcher ed : this.dispatchers) {
			ed.dispatch(e);
		}
	}

	public void entry(EventDispatcher ed) {
		this.dispatchers.add(ed);
	}
}
