package werkzeugkasten.mvnhack;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Configuration;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.impl.DefaultContext;
import werkzeugkasten.mvnhack.repository.impl.FlatDestination;
import werkzeugkasten.mvnhack.repository.impl.LocalRepository;
import werkzeugkasten.mvnhack.repository.impl.PropertiesConfiguration;
import werkzeugkasten.mvnhack.repository.impl.YamlConfiguration;

public class Main {

	public static void main(String[] args) throws Exception {
		new Main().execute(args);
	}

	public Main() {
	}

	protected void execute(String[] args) throws Exception {
		if (args == null || args.length < 2) {
			printHelp();
			return;
		}

		Cmd cmd = parseCommand(args);
		if (cmd == null) {
			printHelp();
			return;
		}

		if (cmd.dependencies == null) {
			Properties props = System.getProperties();
			Configuration config = new PropertiesConfiguration(props);
			addLocalRepository(cmd, config);
			DefaultContext ctx = new DefaultContext(config);
			ctx.resolve(cmd.groupId, cmd.artifactId, cmd.version);
		} else {
			MappingNode node = parseYaml(cmd.dependencies);
			overrideArgs(cmd, node);
			Configuration config = new YamlConfiguration(node);
			addLocalRepository(cmd, config);
			DefaultContext ctx = new DefaultContext(config);
			resolve(ctx, node);
		}

	}

	protected void addLocalRepository(Cmd cmd, Configuration config) {
		if (cmd.isFlat) {
			config.addDestination(new FlatDestination(cmd.destDir));
		} else {
			LocalRepository lr = new LocalRepository(cmd.destDir, null);
			config.addDestination(lr);
		}
	}

	private Cmd parseCommand(String[] args) {
		Cmd cmd = new Cmd();

		List<String> master = Arrays.asList(args);
		List<String> list = new ArrayList<String>(master.size());
		for (String s : master) {
			if (s.startsWith("flat=")) {
				s = s.substring(s.indexOf('=') + 1);
				if ("off".equalsIgnoreCase(s) || "false".equalsIgnoreCase(s)) {
					cmd.isFlat = false;
				}
			} else if (s.startsWith("dest=")) {
				s = s.substring(s.indexOf('=') + 1);
				cmd.destDir = new File(s);
			} else if (s.endsWith(".yml")) {
				File dep = new File(s);
				if (dep.exists() && dep.canRead()) {
					cmd.dependencies = dep;
					return cmd;
				}
			} else {
				list.add(s);
			}
		}
		if (list.size() == 3) {
			cmd.groupId = list.get(0);
			cmd.artifactId = list.get(1);
			cmd.version = list.get(2);
		} else if (list.size() == 2) {
			cmd.groupId = list.get(0);
			cmd.artifactId = list.get(0);
			cmd.version = list.get(1);
		} else if (list.size() == 0) {
			File dep = new File("dependencies.yml");
			if (dep.exists() && dep.canRead()) {
				cmd.dependencies = dep;
				return cmd;
			}
		} else {
			return null;
		}
		return cmd;
	}

	protected class Cmd {
		String groupId = null;
		String artifactId = null;
		String version = null;
		boolean isFlat = true;
		File destDir = new File(".");
		File dependencies;
	}

	protected MappingNode parseYaml(File conf) throws Exception {
		Yaml yaml = new Yaml();
		InputStream in = null;
		try {
			Reader r = new InputStreamReader(new BufferedInputStream(
					new FileInputStream(conf)));
			Node node = yaml.compose(r);
			if (NodeId.mapping.equals(node.getNodeId())) {
				return (MappingNode) node;
			}
			throw new IllegalArgumentException(
					"root node must be mapping node.");
		} finally {
			Streams.close(in);
		}
	}

	protected void overrideArgs(Cmd cmd, MappingNode node) {
		for (NodeTuple nt : node.getValue()) {
			Node keyNode = nt.getKeyNode();
			Node valNode = nt.getKeyNode();
			String key = getValue(keyNode);
			String val = getValue(valNode);
			if (StringUtil.isEmpty(key) == false
					&& StringUtil.isEmpty(val) == false) {
				if ("flatten".equals(key)) {
					cmd.isFlat = val
							.matches("([yY][eE][sS]|[tT][rR][uU][eE]|[oO][nN])");
				} else if ("destination".equals(key)) {
					File dest = new File(val);
					if (dest.exists() && dest.canWrite()) {
						cmd.destDir = dest;
					}
				}
			}
		}
	}

	protected String getValue(Node n) {
		if (NodeId.scalar.equals(n.getNodeId())) {
			ScalarNode sn = (ScalarNode) n;
			return sn.getValue();
		}
		return null;
	}

	protected void resolve(Context context, MappingNode node) {
		for (NodeTuple nt : node.getValue()) {
			String key = getValue(nt.getKeyNode());
			Node an = nt.getValueNode();
			if ("dependencies".equals(key)
					&& NodeId.sequence.equals(an.getNodeId())) {
				SequenceNode sn = (SequenceNode) an;
				for (Node elem : sn.getValue()) {
					if (NodeId.sequence.equals(elem.getNodeId())) {
						SequenceNode artifact = (SequenceNode) elem;
						List<String> list = new ArrayList<String>();
						for (Node n : artifact.getValue()) {
							String s = getValue(n);
							if (StringUtil.isEmpty(s) == false) {
								list.add(s);
							}
						}
						resolve(context, list);
					} else if (NodeId.scalar.equals(elem.getNodeId())) {
						ScalarNode artifact = (ScalarNode) elem;
						String[] arts = getValue(artifact).split("\\s");
						List<String> list = Arrays.asList(arts);
						resolve(context, list);
					}
				}
			}
		}
	}

	protected void resolve(Context context, List<String> artifact) {
		if (artifact.size() == 3) {
			context.resolve(artifact.get(0), artifact.get(1), artifact.get(2));
		} else if (artifact.size() == 2) {
			context.resolve(artifact.get(0), artifact.get(0), artifact.get(1));
		} else {
			Constants.LOG.log(Level.WARNING, artifact.toString());
		}
	}

	protected static final String HELP = "werkzeugkasten/mvnhack/Help.";
	protected static final String HELP_DEFAULT = "werkzeugkasten/mvnhack/Help.en";

	protected void printHelp() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Locale l = Locale.getDefault();
		String help = HELP + l.getLanguage();
		URL u = cl.getResource(help);
		if (u == null) {
			u = cl.getResource(HELP_DEFAULT);
		}
		final URL url = u;
		if (url != null) {
			new Streams.using<InputStream, Exception>() {
				@Override
				public InputStream open() throws Exception {
					return UrlUtil.open(url);
				}

				@Override
				public void handle(InputStream stream) throws Exception {
					BufferedReader r = new BufferedReader(
							new InputStreamReader(stream));
					while (r.ready()) {
						System.out.println(r.readLine());
					}
				}

				@Override
				public void happen(Exception exception) {

				}
			};

		}
	}
}
