package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class ObjectBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		return new ObjectBinder(value);
	}

	@Override
	public Class<?> targetType() {
		return Object.class;
	}

	@Override
	public String bindingTypeName() {
		return "Object";
	}
}
