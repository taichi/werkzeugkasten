package werkzeugkasten.twowaysql.dao.base.binder;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class ByteBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Byte) {
			Byte v = (Byte) value;
			return new ByteBinder(v);
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
