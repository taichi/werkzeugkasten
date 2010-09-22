package org.handwerkszeug.dns.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.handwerkszeug.util.ClassUtil;

import werkzeugkasten.common.util.Streams;

/**
 * <a href="http://www.iana.org/assignments/port-numbers">PORT NUMBERS</a>
 * 
 * @author taichi
 */
public class WKPortNumbers {

	public static final String UNKNOWN_PORT = "unknown";
	public static final String PATH = ClassUtil
			.toPackagePath(WKPortNumbers.class) + "/PortNumbers.txt";

	protected static final Set<String> skipWords = new HashSet<String>();
	static {
		skipWords.add("Reserved");
		skipWords.add("Unassigned");
		skipWords.add("Discard");
	}

	protected Map<Integer, String> ports = new HashMap<Integer, String>();

	public WKPortNumbers() {
	}

	public void load() {
		load(PATH);
	}

	public void load(String path) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		load(cl.getResourceAsStream(path));
	}

	public void load(final InputStream in) {
		new Streams.using<BufferedInputStream, Exception>() {

			@Override
			public BufferedInputStream open() throws Exception {
				return new BufferedInputStream(in);
			}

			@Override
			public void handle(BufferedInputStream stream) throws Exception {
			}

			@Override
			public void happen(Exception exception) {
				throw new IllegalStateException(exception);
			}
		};
	}

	protected void parse(BufferedReader br) throws IOException {
		while (br.ready()) {
			parse(br.readLine());
		}
	}

	protected void parse(String line) {
		String[] ary = line.split("\\p{Space}");
		if ((ary.length < 3) || "#".equals(ary[0])
				|| skipWords.contains(ary[2])) {
			return;
		}

	}
}
