package org.handwerkszeug.util;

import java.io.InputStream;
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

	public void accept(InputStream in) {
		Reader yaml = new InputStreamReader(in);
		Composer composer = new Composer(
				new ParserImpl(new StreamReader(yaml)), new Resolver());
		Node node = composer.getSingleNode();
		this.rootHandler.handle(node);
	}

	public interface Handler {
		String getNodeName();

		void handle(Node node);
	}

	public static abstract class DefaultHandler implements Handler {
		protected String name;

		public DefaultHandler() {
		}

		public DefaultHandler(String name) {
			this.name = name;
		}

		@Override
		public String getNodeName() {
			return this.name;
		}
	}

	public static class MappingHandler extends DefaultHandler {
		Map<String, Handler> handlers = new HashMap<String, YamlNodeAccepter.Handler>();

		public MappingHandler() {
		}

		public MappingHandler(String name) {
			super(name);
		}

		public void add(Handler handler) {
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

	public static class SequenceHandler extends DefaultHandler {
		protected Handler handler;

		public SequenceHandler() {
		}

		public SequenceHandler(Handler handler) {
			this.handler = handler;
		}

		public SequenceHandler(String name, Handler handler) {
			super(name);
			this.handler = handler;
		}

		@Override
		public void handle(Node node) {
			SequenceNode sn = (SequenceNode) node;
			for (Node n : sn.getValue()) {
				this.handler.handle(n);
			}
		}
	}
}
