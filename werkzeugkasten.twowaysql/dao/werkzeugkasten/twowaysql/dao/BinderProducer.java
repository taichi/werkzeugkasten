package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;

public interface BinderProducer {

	Binder produce(Object object) throws SQLRuntimeException;
}
