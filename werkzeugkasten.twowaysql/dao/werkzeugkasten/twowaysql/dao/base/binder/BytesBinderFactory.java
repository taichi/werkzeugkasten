package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class BytesBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof byte[]) {
			byte[] v = (byte[]) value;
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
