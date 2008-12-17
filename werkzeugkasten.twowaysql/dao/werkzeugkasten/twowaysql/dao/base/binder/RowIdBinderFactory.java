package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.RowId;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class RowIdBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		if (value instanceof RowId) {
			RowId v = (RowId) value;
			return new RowIdBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return RowId.class;
	}

	@Override
	public String bindingTypeName() {
		return "RowId";
	}
}
