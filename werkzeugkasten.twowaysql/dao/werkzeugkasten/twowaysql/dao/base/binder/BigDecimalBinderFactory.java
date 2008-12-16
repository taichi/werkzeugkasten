package werkzeugkasten.twowaysql.dao.base.binder;

import java.math.BigDecimal;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class BigDecimalBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof BigDecimal) {
			BigDecimal v = (BigDecimal) value;
			return new BigDecimalBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return BigDecimal.class;
	}

	@Override
	public String bindingTypeName() {
		return "BigDecimal";
	}
}
