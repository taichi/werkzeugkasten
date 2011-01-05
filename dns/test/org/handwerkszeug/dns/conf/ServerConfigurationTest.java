package org.handwerkszeug.dns.conf;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.composer.Composer;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.Event.ID;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.resolver.Resolver;

import werkzeugkasten.common.util.Streams;

public class ServerConfigurationTest {

	// @Test
	public void load() throws Exception {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		final URL url = cl.getResource("named.default.yml");
		new Streams.using<InputStream, Exception>() {
			@Override
			public InputStream open() throws Exception {
				return url.openStream();
			}

			@Override
			public void handle(InputStream stream) throws Exception {
				// Yaml parser = new Yaml();
				// Object o = parser.load(stream);
				// System.out.println(o);
				// for (Node n : parser.composeAll(new
				// InputStreamReader(stream))) {
				// System.out.println(n);
				// }
				event(stream);

			}

			@Override
			public void happen(Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	protected void event(InputStream stream) throws Exception {
		Yaml y = new Yaml();
		for (Event e : y.parse(new InputStreamReader(stream))) {
			System.out.println(e);
		}
	}

	protected void handle(Iterable<Event> iterable) throws Exception {
		for (Iterator<Event> i = iterable.iterator(); i.hasNext();) {
			Event current = i.next();
			if (current instanceof ScalarEvent) {
				ScalarEvent se = (ScalarEvent) current;
				if ("bindingHosts".equalsIgnoreCase(se.getValue())
						&& i.hasNext()) {
					Event e = i.next();
					if (e.is(ID.SequenceStart)) {
						while (e.is(ID.SequenceEnd) == false) {
							if (e.is(ID.MappingStart)) {
								// TODO validate format
							} else if (e.is(ID.Scalar)) {

							}

							if (i.hasNext()) {
								e = i.next();
							} else {
								break;
							}
						}
					}
				}
			}
		}
	}

	protected void compose(InputStream stream) throws Exception {
		final Composer composer = new Composer(new ParserImpl(new StreamReader(
				new InputStreamReader(stream))), new Resolver());
		while (composer.checkNode()) {
			System.out.println(composer.getNode());
		}
	}

}
