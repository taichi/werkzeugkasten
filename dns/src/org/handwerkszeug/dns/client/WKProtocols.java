package org.handwerkszeug.dns.client;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.handwerkszeug.util.ClassUtil;
import org.handwerkszeug.util.Streams;
import org.handwerkszeug.util.StringUtil;

public class WKProtocols {

	public static final String UNKNOWN_PROTOCOL = "UNKNOWN";
	public static final String PATH = ClassUtil
			.toPackagePath(WKProtocols.class) + "/ProtocolNumbers.xml";

	protected Map<Integer, String> ports = new HashMap<Integer, String>();

	public WKProtocols() {
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

	protected void parse(BufferedInputStream stream) throws Exception {
	}

	protected void add(int value, String name) {
		this.ports.put(value, name);
	}

	public String find(short ubyte) {
		String s = this.ports.get(ubyte);
		if (StringUtil.isEmpty(s)) {
			return UNKNOWN_PROTOCOL;
		}
		return s;
	}
}
