package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class BytesBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		byte[] v = ConverterUtil.convert(value, byte[].class);
		if (v != null) {
			return new BytesBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return byte[].class;
	}

	@Override
	public String bindingTypeName() {
		return "Bytes";
	}
}
