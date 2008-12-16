package werkzeugkasten.twowaysql.dao.base.binder;

import java.io.InputStream;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class BinaryStreamBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof InputStream) {
			InputStream v = (InputStream) value;
			return new BinaryStreamBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return InputStream.class;
	}

	@Override
	public String bindingTypeName() {
		return "BinaryStream";
	}

}
