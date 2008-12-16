package werkzeugkasten.twowaysql.dao.base.binder;

import java.io.Reader;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class CharacterStreamBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Reader) {
			Reader v = (Reader) value;
			return new CharacterStreamBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Reader.class;
	}

	@Override
	public String bindingTypeName() {
		return "CharacterStream";
	}
}
