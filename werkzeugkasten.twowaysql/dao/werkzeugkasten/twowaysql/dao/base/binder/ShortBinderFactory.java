package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class ShortBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Short) {
			Short v = (Short) value;
			return new ShortBinder(v);
		} else if (value instanceof Number) {
			Number v = (Number) value;
			return new ShortBinder(v.shortValue());
		} else if (value instanceof String) {
			String v = (String) value;
			return new ShortBinder(Short.parseShort(v));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Short.class;
	}

	@Override
	public String bindingTypeName() {
		return "Short";
	}
}
