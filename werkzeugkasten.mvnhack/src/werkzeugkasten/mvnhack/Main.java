package werkzeugkasten.mvnhack;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.common.util.UrlUtil;

public class Main {

	public static void main(String[] args) {
		new Main().execute(args);
	}

	public Main() {
	}

	protected void execute(String[] args) {
		if (args == null || args.length < 1) {
			printHelp();
			return;
		}

		String groupId = null;
		String artifactId = null;
		String version = null;

	}

	protected static final String HELP = "werkzeugkasten/mvnhack/Help.";
	protected static final String HELP_DEFAULT = "werkzeugkasten/mvnhack/Help.en";

	protected void printHelp() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Locale l = Locale.getDefault();
		String help = HELP + l.getLanguage();
		URL url = cl.getResource(help);
		if (url == null) {
			url = cl.getResource(HELP_DEFAULT);
		}

		if (url != null) {
			InputStream in = null;
			try {
				in = UrlUtil.open(url);
				BufferedReader r = new BufferedReader(new InputStreamReader(in));
				while (r.ready()) {
					System.out.println(r.readLine());
				}
			} catch (Exception e) {
			} finally {
				StreamUtil.close(in);
			}
		}
	}
}
