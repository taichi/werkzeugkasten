package werkzeugkasten.mvnhack.repository.impl;

import java.util.logging.Level;

import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.Constants;

public class YamlConfiguration extends AbstractConfiguration {

	public YamlConfiguration() {
	}

	public YamlConfiguration(MappingNode node) {
		load();
		load(node);
	}

	public void load(MappingNode node) {
		for (NodeTuple nt : node.getValue()) {
			Node kn = nt.getKeyNode();
			Node vn = nt.getValueNode();
			if (NodeId.scalar.equals(kn.getNodeId())) {
				ScalarNode ksn = (ScalarNode) kn;
				if (Constants.NODE_REPOSITORIES.equals(ksn.getValue())) {
					if (NodeId.scalar.equals(vn.getNodeId())) {
						ScalarNode vsn = (ScalarNode) vn;
						addRepository(vsn);
					} else if (NodeId.sequence.equals(vn.getNodeId())) {
						SequenceNode vsn = (SequenceNode) vn;
						for (Node n : vsn.getValue()) {
							if (NodeId.scalar.equals(n.getNodeId())) {
								ScalarNode sn = (ScalarNode) n;
								addRepository(sn);
							}
						}
					}
				} else if (Constants.PROP_PROXY.equals(ksn.getValue())) {
					if (NodeId.scalar.equals(vn.getNodeId())) {
						ScalarNode vsn = (ScalarNode) vn;
						UrlUtil.setUpProxy(vsn.getValue());
					} else {
						Constants.LOG.log(Level.WARNING, nt.toString());
					}
				} else if (Constants.PROP_NONPROXY.equals(ksn.getValue())
						&& StringUtil.isEmpty(System
								.getProperty(Constants.PROP_NONPROXY))) {
					if (NodeId.scalar.equals(vn.getNodeId())) {
						ScalarNode vsn = (ScalarNode) vn;
						String v = vsn.getValue();
						if (StringUtil.isEmpty(v) == false) {
							System.setProperty(Constants.PROP_NONPROXY, v);
						}
					} else if (NodeId.sequence.equals(vn.getNodeId())) {
						SequenceNode vsn = (SequenceNode) vn;
						StringBuilder stb = new StringBuilder();
						for (Node n : vsn.getValue()) {
							if (NodeId.scalar.equals(n.getNodeId())) {
								ScalarNode sn = (ScalarNode) n;
								stb.append(sn.getValue());
								stb.append(',');
							}
						}
						System.setProperty(Constants.PROP_NONPROXY,
								stb.toString());
					}
				}
			}
		}
	}

	public void addRepository(ScalarNode sn) {
		String s = sn.getValue();
		if (validateURL(s)) {
			addRepository(new RemoteRepository(s, builder));
		} else {
			Constants.LOG.log(Level.SEVERE, "invalid url " + s);
		}
	}
}
