package org.handwerkszeug.container.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableEventDispatcher implements EventDispatcher {

	protected Map<String, List<EventHandler>> table;

	public TableEventDispatcher() {
		this.table = new HashMap<String, List<EventHandler>>();
	}

	public TableEventDispatcher(Map<String, List<EventHandler>> table) {
		this.table = table;
	}

	@Override
	public void dispatch(Event e) {
		List<EventHandler> list = this.table.get(e.name());
		if (list != null) {
			for (EventHandler h : list) {
				h.handle(e.context());
			}
		}
	}

	public void entry(String name, EventHandler handler) {
		List<EventHandler> list = this.table.get(name);
		if (list == null) {
			list = new ArrayList<EventHandler>();
			this.table.put(name, list);
		}
		list.add(handler);
	}
}
