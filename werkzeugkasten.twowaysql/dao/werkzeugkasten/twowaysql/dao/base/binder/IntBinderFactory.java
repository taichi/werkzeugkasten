package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class IntBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		Integer v = ConverterUtil.convert(value, Integer.class);
		if (v != null) {
			return new IntBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Integer.class;
	}

	@Override
	public String bindingTypeName() {
		return "Int";
	}
}
