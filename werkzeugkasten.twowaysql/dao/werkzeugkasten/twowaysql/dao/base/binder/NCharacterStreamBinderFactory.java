package werkzeugkasten.twowaysql.dao.base.binder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class NCharacterStreamBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Reader) {
			Reader v = (Reader) value;
			return new NCharacterStreamBinder(v);
		} else if (value instanceof InputStream) {
			InputStream v = (InputStream) value;
			return new NCharacterStreamBinder(new InputStreamReader(v));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Reader.class;
	}

	@Override
	public String bindingTypeName() {
		return "NCharacterStream";
	}
}
