package org.handwerkszeug.dns.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.handwerkszeug.util.ClassUtil;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.StringUtil;

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
		new Streams.using<BufferedReader, Exception>() {

			@Override
			public BufferedReader open() throws Exception {
				return new BufferedReader(new InputStreamReader(in));
			}

			@Override
			public void handle(BufferedReader stream) throws Exception {
				WKPortNumbers.this.parse(stream);

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
		if (line.startsWith("#")) {
			return;
		}
		String[] ary = line.split("\\p{Space}+");
		if ((ary.length < 3) || skipWords.contains(ary[2])) {
			return;
		}
		int index = ary[1].indexOf('/');
		String port = ary[1].substring(0, index);
		add(Integer.valueOf(port), ary[0]);
	}

	public void add(Integer port, String keyword) {
		this.ports.put(port, keyword);
	}

	public String find(Integer port) {
		String keyword = this.ports.get(port);
		if (StringUtil.isEmpty(keyword)) {
			return UNKNOWN_PORT;
		}
		return keyword;
	}
}
