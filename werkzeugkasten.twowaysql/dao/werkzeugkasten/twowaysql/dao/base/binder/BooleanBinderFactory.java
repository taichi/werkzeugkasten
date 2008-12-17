package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class BooleanBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		Boolean v = ConverterUtil.convert(value, Boolean.class);
		if (v != null) {
			return new BooleanBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Boolean.class;
	}

	@Override
	public String bindingTypeName() {
		return "Boolean";
	}
}
