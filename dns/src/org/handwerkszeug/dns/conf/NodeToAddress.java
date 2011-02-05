package org.handwerkszeug.dns.conf;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.handwerkszeug.dns.Constants;
import org.handwerkszeug.dns.Markers;
import org.handwerkszeug.dns.nls.Messages;
import org.handwerkszeug.util.AddressUtil;
import org.handwerkszeug.yaml.DefaultHandler;
import org.handwerkszeug.yaml.YamlNodeHandler;
import org.handwerkszeug.yaml.YamlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;

import werkzeugkasten.common.util.StringUtil;

public class NodeToAddress extends DefaultHandler<List<InetSocketAddress>> {

	static final Logger LOG = LoggerFactory.getLogger(NodeToAddress.class);
	static final Map<NodeId, YamlNodeHandler<List<InetSocketAddress>>> converters = new HashMap<NodeId, YamlNodeHandler<List<InetSocketAddress>>>();
	static {
		converters.put(NodeId.scalar, new ScalarToAddress());
		converters.put(NodeId.mapping, new MappingToAddress());
	}

	public NodeToAddress() {
	}

	@Override
	public void handle(Node node, List<InetSocketAddress> context) {
		YamlNodeHandler<List<InetSocketAddress>> handler = converters.get(node
				.getNodeId());
		if (handler == null) {
			LOG.debug(Markers.DETAIL, Messages.UnsupportedNode, node);
		} else {
			handler.handle(node, context);
		}
	}

	static class ScalarToAddress extends
			DefaultHandler<List<InetSocketAddress>> {
		protected int defaultPort = Constants.DEFAULT_PORT;

		public ScalarToAddress() {
		}

		public ScalarToAddress(int port) {
			this.defaultPort = port;
		}

		@Override
		public void handle(Node node, List<InetSocketAddress> context) {
			if (node instanceof ScalarNode) {
				ScalarNode sn = (ScalarNode) node;
				InetSocketAddress addr = AddressUtil.convertTo(sn.getValue(),
						this.defaultPort);
				if (addr != null) {
					context.add(addr);
				} else {
					LOG.debug(Markers.DETAIL, Messages.InvalidAddressFormat,
							sn.getValue());
				}
			}
		}
	}

	static class MappingToAddress extends
			DefaultHandler<List<InetSocketAddress>> {

		protected int defaultPort = Constants.DEFAULT_PORT;

		public MappingToAddress() {
		}

		public MappingToAddress(int port) {
			this.defaultPort = port;
		}

		@Override
		public void handle(Node node, List<InetSocketAddress> context) {
			if (node instanceof MappingNode) {
				MappingNode mn = (MappingNode) node;
				String[] ary = new String[2];
				for (NodeTuple nt : mn.getValue()) {
					String key = YamlUtil.getStringValue(nt.getKeyNode());
					String value = YamlUtil.getStringValue(nt.getValueNode());
					if ("address".equalsIgnoreCase(key)) {
						ary[0] = value;
					} else if ("port".equalsIgnoreCase(key)) {
						ary[1] = value;
					} else {
						ary[0] = key;
						ary[1] = value;
						break;
					}
				}
				if (StringUtil.isEmpty(ary[0]) == false) {
					InetSocketAddress addr = AddressUtil.convertTo(ary[0],
							AddressUtil.toInt(ary[1], this.defaultPort));
					if (addr != null) {
						context.add(addr);
					} else {
						LOG.debug(Markers.DETAIL,
								Messages.InvalidAddressFormat, mn.getValue());
					}
				}
			}
		}
	}
}
