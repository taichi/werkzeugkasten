package org.handwerkszeug.dns.conf;

import java.util.HashMap;
import java.util.Map;

import org.handwerkszeug.dns.Markers;
import org.handwerkszeug.dns.nls.Messages;
import org.handwerkszeug.yaml.DefaultHandler;
import org.handwerkszeug.yaml.YamlNodeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

public class NodeToAddress extends DefaultHandler<ServerConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(NodeToAddress.class);
	static final Map<NodeId, YamlNodeHandler<ServerConfiguration>> converters = new HashMap<NodeId, YamlNodeHandler<ServerConfiguration>>();
	static {
		converters.put(NodeId.scalar, new ScalarToAddress());
		converters.put(NodeId.mapping, new MappingToAddress());
	}

	public NodeToAddress() {
	}

	@Override
	public void handle(Node node, ServerConfiguration context) {
		YamlNodeHandler<ServerConfiguration> handler = converters.get(node
				.getNodeId());
		if (handler == null) {
			LOG.debug(Markers.DETAIL, Messages.UnsupportedNode, node);
		} else {
			handler.handle(node, context);
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
