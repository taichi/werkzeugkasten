package org.handwerkszeug.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.Event.ID;
import org.yaml.snakeyaml.parser.Parser;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;

public class YamlEventParser {

	protected final Parser parser;

	public YamlEventParser(Parser parser) {
		this.parser = parser;
	}

	public YamlEventParser(InputStream in) {
		this(new ParserImpl(new StreamReader(new InputStreamReader(in))));
	}

	public void parse() {
		skip(2);
		parse(this.parser.getEvent());
		skip(2);
	}

	public void parse(Event current) {
		for (Event e = current; this.parser.peekEvent() != null; e = this.parser
				.getEvent()) {
			if (e.is(ID.MappingStart)) {

			} else if (e.is(ID.SequenceStart)) {

			}
		}
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

	public interface Handler<T> {
		// String getNodeName();

		T handle(Parser parser);
	}

	public static abstract class SequenceHandler<T> implements Handler<List<T>> {
		@Override
		public List<T> handle(Parser parser) {
			List<T> result = new ArrayList<T>();
			for (Event e = parser.getEvent(); parser.peekEvent() != null; e = parser
					.getEvent()) {
				if (e.is(ID.SequenceEnd)) {
					break;
				}
				result.add(handle(e));
			}
			return result;
		}

		protected abstract T handle(Event e);
	}

	public static abstract class MappingHandler<T> implements Handler<T> {
	}
}
