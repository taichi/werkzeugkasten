package org.handwerkszeug.dns.conf;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.composer.Composer;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.resolver.Resolver;

import werkzeugkasten.common.util.Streams;

public class ServerConfigurationTest {

	@Test
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

	protected void compose(InputStream stream) throws Exception {
		final Composer composer = new Composer(new ParserImpl(new StreamReader(
				new InputStreamReader(stream))), new Resolver());
		while (composer.checkNode()) {
			System.out.println(composer.getNode());
		}
	}
}
