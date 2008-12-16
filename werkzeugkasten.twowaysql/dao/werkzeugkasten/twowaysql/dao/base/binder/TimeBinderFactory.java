package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Time;
import java.util.Date;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class TimeBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Time) {
			Time v = (Time) value;
			return new TimeBinder(v);
		} else if (value instanceof Date) {
			Date v = (Date) value;
			return new TimeBinder(new Time(v.getTime()));
		} else if (value instanceof Number) {
			Number v = (Number) value;
			return new TimeBinder(new Time(v.longValue()));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Time.class;
	}

	@Override
	public String bindingTypeName() {
		return "Time";
	}
}
