package werkzeugkasten.twowaysql.dao.base.binder;

import java.math.BigDecimal;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class BigDecimalBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		BigDecimal v = ConverterUtil.convert(value, BigDecimal.class);
		if (v != null) {
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
