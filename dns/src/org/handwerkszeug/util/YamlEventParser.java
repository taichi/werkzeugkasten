package org.handwerkszeug.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.Event.ID;
import org.yaml.snakeyaml.parser.Parser;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;

public class YamlEventParser {

	protected Map<String, Handler> handlers = new HashMap<String, YamlEventParser.Handler>();
	protected final Parser parser;

	public YamlEventParser(Parser parser) {
		this.parser = parser;
	}

	public YamlEventParser(InputStream in) {
		this(new ParserImpl(new StreamReader(new InputStreamReader(in))));
	}

	public void add(Handler handler) {
		if (handler != null) {
			this.handlers.put(handler.getNodeName(), handler);
		}
	}

	public void parse() {
		skip(2);

		for (Event e = this.parser.getEvent(); this.parser.peekEvent() != null; e = this.parser
				.getEvent()) {
			if (e.is(ID.Scalar)) {

			} else if (e.is(ID.MappingStart)) {

			} else if (e.is(ID.SequenceStart)) {

			}
		}

		skip(2);
	}

	protected void skip(int count) {
		for (int i = 0; i < count; i++) {
			this.parser.getEvent();
		}
	}

	protected void skip(Event current) {
		if ((current == null) || current.is(Event.ID.Scalar)) {
			this.parser.peekEvent();
			return;
		}
		Deque<Event> inline = new LinkedList<Event>();
		for (Event e = this.parser.getEvent(); this.parser.peekEvent() != null; e = this.parser
				.getEvent()) {
			if (e.is(ID.MappingStart) || e.is(ID.SequenceStart)) {
				inline.addLast(current);
				current = e;
			} else if ((e.is(ID.MappingEnd) && current.is(ID.MappingStart))
					|| (e.is(ID.SequenceEnd) && current.is(ID.SequenceStart))) {
				if (inline.size() < 1) {
					break;
				} else {
					current = inline.removeLast();
				}
			}
		}
	}

	public interface Handler {
		String getNodeName();

		void handle(Parser parser);
	}
}
