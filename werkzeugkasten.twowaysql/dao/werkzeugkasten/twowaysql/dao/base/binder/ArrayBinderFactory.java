package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Array;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class ArrayBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		if (value instanceof Array) {
			Array v = (Array) value;
			return new ArrayBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Array.class;
	}

	@Override
	public String bindingTypeName() {
		return "Array";
	}

}
