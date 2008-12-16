package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class ByteBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		Byte v = ConverterUtil.convert(value, Byte.class);
		if (v != null) {
			return new ByteBinder(v.byteValue());
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Byte.class;
	}

	@Override
	public String bindingTypeName() {
		return "Byte";
	}
}
