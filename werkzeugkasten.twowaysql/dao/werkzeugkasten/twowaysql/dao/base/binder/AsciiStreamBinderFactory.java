package werkzeugkasten.twowaysql.dao.base.binder;

import java.io.InputStream;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class AsciiStreamBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		if (value instanceof InputStream) {
			InputStream v = (InputStream) value;
			return new AsciiStreamBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return InputStream.class;
	}

	@Override
	public String bindingTypeName() {
		return "AsciiStream";
	}
}
