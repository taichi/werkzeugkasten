package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class ShortBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		Short v = ConverterUtil.convert(value, Short.class);
		if (v != null) {
			return new ShortBinder(v);
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
