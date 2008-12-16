package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class BooleanBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Boolean) {
			Boolean v = (Boolean) value;
			return new BooleanBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Boolean.class;
	}

	@Override
	public String bindingTypeName() {
		return "Boolean";
	}
}
