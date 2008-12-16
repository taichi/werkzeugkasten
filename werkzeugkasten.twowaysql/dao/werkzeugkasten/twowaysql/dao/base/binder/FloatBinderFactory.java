package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class FloatBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Float) {
			Float v = (Float) value;
			return new FloatBinder(v);
		} else if (value instanceof Number) {
			Number v = (Number) value;
			return new FloatBinder(v.floatValue());
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Float.class;
	}

	@Override
	public String bindingTypeName() {
		return "Float";
	}
}
