package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;

public interface BinderFactory {

	Binder wrap(Object object) throws SQLRuntimeException;
}
