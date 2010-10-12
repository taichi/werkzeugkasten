package org.handwerkszeug.dns.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.handwerkszeug.chain.Chain;
import org.handwerkszeug.chain.ChainResult;
import org.handwerkszeug.chain.impl.SimpleChainResult;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.StringUtil;

public class ResolveConf implements Chain<List<String>, ChainResult> {

	@Override
	public ChainResult execute(List<String> context) {
		handle("/etc/resolv.conf", context);
		return SimpleChainResult.Continue;
	}

	protected void handle(String path, final List<String> list) {
		final File file = new File(path);
		if (file.exists()) {
			new Streams.using<BufferedReader, Exception>() {
				@Override
				public BufferedReader open() throws Exception {
					return new BufferedReader(new InputStreamReader(
							new FileInputStream(file)));
				}

				@Override
				public void handle(BufferedReader stream) throws Exception {
					while (stream.ready()) {
						parse(stream.readLine(), list);
					}
				}

				@Override
				public void happen(Exception exception) {
					// suppress error
				}
			};
		}
	}

	protected void parse(String line, List<String> list) {
		if (StringUtil.isEmpty(line) == false) {
			String[] ary = line.split("\\s");
			if ((1 < ary.length) && ary[0].equalsIgnoreCase("nameserver")) {
				list.add(ary[1]);
			}
		}
	}
}
