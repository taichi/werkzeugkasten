package org.handwerkszeug.util;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.composer.Composer;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.resolver.Resolver;

public class YamlNodeAccepter {

	protected final Handler rootHandler;

	protected YamlNodeAccepter(Handler root) {
		this.rootHandler = root;
	}

	public void accept() {

	}

	public static void main(String[] args) {
		Reader yaml = new InputStreamReader(
				YamlNodeAccepter.class
						.getResourceAsStream("/named.default.yml"));
		Composer composer = new Composer(
				new ParserImpl(new StreamReader(yaml)), new Resolver());
		Node node = composer.getSingleNode();
		System.out.println(node);
		MappingNode mn = (MappingNode) node;
		for (NodeTuple nt : mn.getValue()) {
			Node key = nt.getKeyNode();
			if (key instanceof ScalarNode) {
				ScalarNode sn = (ScalarNode) key;
				Node value = nt.getValueNode();
				String s = sn.getValue();
				if ("bindingHosts".equalsIgnoreCase(s)) {
					bindingHosts(value);
				}
				if ("logging".equalsIgnoreCase(s)) {
					logging(value);
				}
				if ("threadPoolSize".equalsIgnoreCase(s)) {
					threadPoolSize(value);
				}
			}
		}
	}

	public interface Handler {
		void handle(Node node);
	}

	public interface NamedHandler extends Handler {
		String getNodeName();
	}

	public static abstract class MappingHandler implements Handler {
		Map<String, Handler> handlers = new HashMap<String, YamlNodeAccepter.Handler>();

		public void add(NamedHandler handler) {
			if (handler != null) {
				this.handlers.put(handler.getNodeName(), handler);
			}
		}

		@Override
		public void handle(Node node) {
			MappingNode mn = (MappingNode) node;
			for (NodeTuple nt : mn.getValue()) {
				Node key = nt.getKeyNode();
				if (key.getNodeId().equals(NodeId.scalar)) {
					ScalarNode sn = (ScalarNode) key;
					Handler h = this.handlers.get(sn.getValue());
					if (h != null) {
						Node value = nt.getValueNode();
						h.handle(value);
					}
				}
			}
		}
	}

	public static abstract class SequenceHandler implements Handler {
		@Override
		public void handle(Node node) {
			SequenceNode sn = (SequenceNode) node;
			for (Node n : sn.getValue()) {
				handleElement(n);
			}
		}

		protected abstract void handleElement(Node node);
	}

	static void bindingHosts(Node node) {

	}

	static void logging(Node node) {

	}

	static void threadPoolSize(Node node) {

	}
}
