package org.handwerkszeug.dns.conf;

import java.util.HashMap;
import java.util.Map;

import org.handwerkszeug.yaml.DefaultHandler;
import org.handwerkszeug.yaml.YamlNodeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

public class Address extends DefaultHandler<ServerConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(Address.class);
	static final Map<NodeId, YamlNodeHandler<ServerConfiguration>> converters = new HashMap<NodeId, YamlNodeHandler<ServerConfiguration>>();

	@Override
	public void handle(Node node, ServerConfiguration context) {
		YamlNodeHandler<ServerConfiguration> ynh = converters.get(node
				.getNodeId());
		if (ynh == null) {

		} else {
			ynh.handle(node, context);
		}
	}

	static class ScalarToAddress extends DefaultHandler<ServerConfiguration> {
		@Override
		public void handle(Node node, ServerConfiguration context) {
			// TODO
			System.out.println(node);
		}
	}

	static class MappingToAddress extends DefaultHandler<ServerConfiguration> {
		@Override
		public void handle(Node node, ServerConfiguration context) {
			// TODO
			System.out.println(node);
		}
	}
}
