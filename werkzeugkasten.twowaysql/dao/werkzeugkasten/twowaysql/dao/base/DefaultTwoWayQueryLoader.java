package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.TwoWayQueryLoader;
import werkzeugkasten.twowaysql.dao.TwoWayQueryWrapper;

public class DefaultTwoWayQueryLoader implements TwoWayQueryLoader<String> {

	@Override
	public TwoWayQueryWrapper load(String context) {

		return null;
	}

	protected String loadSource(String context) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		cl.getResourceAsStream(context);
		return null;
	}
}
