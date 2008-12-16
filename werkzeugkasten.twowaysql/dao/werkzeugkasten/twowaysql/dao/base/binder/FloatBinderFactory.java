package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class FloatBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		Float v = ConverterUtil.convert(value, Float.class);
		if (v != null) {
			return new FloatBinder(v);
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
