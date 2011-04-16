package werkzeugkasten.mvnhack.repository.impl;

import java.util.Properties;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.Constants;

public class PropertiesConfiguration extends AbstractConfiguration {

	public PropertiesConfiguration() {
	}

	public PropertiesConfiguration(Properties properties) {
		load();
		load(properties);
	}

	protected void load(Properties properties) {
		String rs = properties.getProperty(Constants.PROP_REPOSITORY);
		if (StringUtil.isEmpty(rs) == false) {
			for (String s : rs.split(",")) {
				if (validateURL(s)) {
					addRepository(new RemoteRepository(s, builder));
				}
			}
		}
		UrlUtil.setUpProxy(properties.getProperty(Constants.PROP_PROXY));
		String hosts = properties.getProperty(Constants.PROP_NONPROXY);
		if (StringUtil.isEmpty(hosts) == false) {
			System.setProperty(Constants.PROP_NONPROXY, hosts);
		}
	}
}
