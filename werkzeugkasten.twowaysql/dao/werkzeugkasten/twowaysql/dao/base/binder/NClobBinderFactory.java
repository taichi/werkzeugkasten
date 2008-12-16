package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.NClob;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class NClobBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof NClob) {
			NClob v = (NClob) value;
			return new NClobBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return NClob.class;
	}

	@Override
	public String bindingTypeName() {
		return "NClob";
	}
}
