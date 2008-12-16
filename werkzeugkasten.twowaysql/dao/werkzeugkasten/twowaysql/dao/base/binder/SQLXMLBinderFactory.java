package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.SQLXML;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class SQLXMLBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) {
		if (value instanceof SQLXML) {
			SQLXML v = (SQLXML) value;
			return new SQLXMLBinder(v);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return SQLXML.class;
	}

	@Override
	public String bindingTypeName() {
		return "SQLXML";
	}
}
