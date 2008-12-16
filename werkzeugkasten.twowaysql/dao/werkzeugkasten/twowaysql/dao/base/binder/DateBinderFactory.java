package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Date;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class DateBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		Date v = ConverterUtil.convert(value, Date.class);
		if (v != null) {
			return new DateBinder(v);
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
