package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class DoubleBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Double) {
			Double v = (Double) value;
			return new DoubleBinder(v);
		} else if (value instanceof Number) {
			Number v = (Number) value;
			return new DoubleBinder(v.doubleValue());
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
