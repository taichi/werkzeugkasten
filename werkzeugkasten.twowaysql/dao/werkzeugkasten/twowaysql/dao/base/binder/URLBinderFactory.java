package werkzeugkasten.twowaysql.dao.base.binder;

import java.net.MalformedURLException;
import java.net.URL;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class URLBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		try {
			if (value instanceof URL) {
				URL v = (URL) value;
				return new URLBinder(v);
			} else if (value instanceof String) {
				String v = (String) value;
				return new URLBinder(new URL(v));
			}
			throw new IllegalArgumentException();
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public Class<?> targetType() {
		return URL.class;
	}

	@Override
	public String bindingTypeName() {
		return "URL";
	}
}
