package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class NStringBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof String) {
			String v = (String) value;
			return new NStringBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return String.class;
	}

	@Override
	public String bindingTypeName() {
		return "NString";
	}
}
