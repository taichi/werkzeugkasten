package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Ref;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class RefBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		if (value instanceof Ref) {
			Ref v = (Ref) value;
			return new RefBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Ref.class;
	}

	@Override
	public String bindingTypeName() {
		return "Ref";
	}
}
