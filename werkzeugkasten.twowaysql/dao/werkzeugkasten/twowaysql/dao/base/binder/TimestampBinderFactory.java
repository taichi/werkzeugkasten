package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Timestamp;
import java.util.Date;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class TimestampBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Timestamp) {
			Timestamp v = (Timestamp) value;
			return new TimestampBinder(v);
		} else if (value instanceof Date) {
			Date v = (Date) value;
			return new TimestampBinder(new Timestamp(v.getTime()));
		} else if (value instanceof Number) {
			Number v = (Number) value;
			return new TimestampBinder(new Timestamp(v.longValue()));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Timestamp.class;
	}

	@Override
	public String bindingTypeName() {
		return "Timestamp";
	}
}
