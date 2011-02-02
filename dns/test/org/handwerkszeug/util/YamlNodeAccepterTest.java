package org.handwerkszeug.util;

import org.handwerkszeug.util.YamlNodeAccepter.DefaultHandler;
import org.handwerkszeug.util.YamlNodeAccepter.MappingHandler;
import org.handwerkszeug.util.YamlNodeAccepter.SequenceHandler;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.nodes.Node;

public class YamlNodeAccepterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAccept() throws Exception {
		MappingHandler root = new MappingHandler();
		root.add(new SequenceHandler("bindingHosts", new DefaultHandler() {
			@Override
			public void handle(Node node) {
				System.out.println(node);
			}
		}));
		root.add(new DefaultHandler("logging") {
			@Override
			public void handle(Node node) {
				System.out.println(node);
			}
		});
		root.add(new DefaultHandler("threadPoolSize") {
			@Override
			public void handle(Node node) {
				System.out.println(node);
			}
		});

		YamlNodeAccepter accepter = new YamlNodeAccepter(root);
		accepter.accept(YamlNodeAccepter.class
				.getResourceAsStream("/named.default.yml"));
	}
}
