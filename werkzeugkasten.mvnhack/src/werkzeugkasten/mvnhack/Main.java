package werkzeugkasten.mvnhack;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Configuration;
import werkzeugkasten.mvnhack.repository.impl.DefaultContext;
import werkzeugkasten.mvnhack.repository.impl.FlatDestination;
import werkzeugkasten.mvnhack.repository.impl.LocalRepository;
import werkzeugkasten.mvnhack.repository.impl.PropertiesConfiguration;

public class Main {

	public static void main(String[] args) {
		new Main().execute(args);
	}

	public Main() {
	}

	protected void execute(String[] args) {
		if (args == null || args.length < 2) {
			printHelp();
			return;
		}

		Cmd cmd = parseCommand(args);
		if (cmd == null) {
			printHelp();
			return;
		}

		Configuration config = null;
		if (cmd.dependencies == null) {
			Properties props = System.getProperties();
			config = new PropertiesConfiguration(props);
		} else {
			config = new PropertiesConfiguration();
		}

		if (cmd.isFlat) {
			config.addDestination(new FlatDestination(cmd.destDir));
		} else {
			LocalRepository lr = new LocalRepository(cmd.destDir, null);
			config.addDestination(lr);
		}

		DefaultContext ctx = new DefaultContext(config);
		ctx.resolve(cmd.groupId, cmd.artifactId, cmd.version);
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
