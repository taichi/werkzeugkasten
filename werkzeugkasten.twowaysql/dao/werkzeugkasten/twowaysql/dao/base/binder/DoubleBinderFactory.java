package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class DoubleBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		Double v = ConverterUtil.convert(value, Double.class);
		if (v != null) {
			return new DoubleBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Double.class;
	}

	@Override
	public String bindingTypeName() {
		return "Double";
	}
}
