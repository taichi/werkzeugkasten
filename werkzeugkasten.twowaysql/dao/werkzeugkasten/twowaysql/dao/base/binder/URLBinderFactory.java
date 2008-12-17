package werkzeugkasten.twowaysql.dao.base.binder;

import java.net.URL;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class URLBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		URL v = ConverterUtil.convert(value, URL.class);
		if (v != null) {
			return new URLBinder(v);
		}
		throw new IllegalArgumentException();
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
