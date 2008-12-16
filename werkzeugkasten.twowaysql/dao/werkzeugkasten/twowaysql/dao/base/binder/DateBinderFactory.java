package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Date;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class DateBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Date) {
			Date v = (Date) value;
			return new DateBinder(v);
		} else if (value instanceof java.util.Date) {
			java.util.Date v = (java.util.Date) value;
			return new DateBinder(v);
		} else if (value instanceof Number) {
			Number v = (Number) value;
			return new DateBinder(new Date(v.longValue()));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Date.class;
	}

	@Override
	public String bindingTypeName() {
		return "Date";
	}
}
