package org.handwerkszeug.dns.conf;

import java.util.List;

import org.handwerkszeug.chain.Chain;
import org.handwerkszeug.chain.ChainResult;
import org.handwerkszeug.chain.impl.SimpleChainResult;

import werkzeugkasten.common.util.StringUtil;

public class SystemProperties implements Chain<List<String>, ChainResult> {

	public static final String SYSTEM_PROPERTY_NAME = "org.handwerkszeug.dns.nameservers";

	@Override
	public ChainResult execute(List<String> context) {
		String servers = System.getProperty(SYSTEM_PROPERTY_NAME);
		if (StringUtil.isEmpty(servers) == false) {
			for (String s : servers.split(",")) {
				context.add(s);
			}
		}
		return SimpleChainResult.Continue;
	}
}
