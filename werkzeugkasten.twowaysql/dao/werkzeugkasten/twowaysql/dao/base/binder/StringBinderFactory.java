package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class StringBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof String) {
			String v = (String) value;
			return new StringBinder(v);
		} else if (value != null) {
			return new StringBinder(value.toString());
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return String.class;
	}

	@Override
	public String bindingTypeName() {
		return "String";
	}
}
