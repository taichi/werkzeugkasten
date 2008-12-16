package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Time;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class TimeBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		Time v = ConverterUtil.convert(value, Time.class);
		if (v != null) {
			return new TimeBinder(v);
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
