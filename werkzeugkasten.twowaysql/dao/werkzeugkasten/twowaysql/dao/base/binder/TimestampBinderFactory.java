package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Timestamp;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class TimestampBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		Timestamp v = ConverterUtil.convert(value, Timestamp.class);
		if (v != null) {
			return new TimestampBinder(v);
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
