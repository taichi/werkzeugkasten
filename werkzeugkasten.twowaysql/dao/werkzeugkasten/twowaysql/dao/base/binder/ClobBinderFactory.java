package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Clob;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class ClobBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof Clob) {
			Clob v = (Clob) value;
			return new ClobBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Clob.class;
	}

	@Override
	public String bindingTypeName() {
		return "Clob";
	}
}
