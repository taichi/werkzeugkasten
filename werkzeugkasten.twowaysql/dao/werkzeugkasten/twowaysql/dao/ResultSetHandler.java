package werkzeugkasten.twowaysql.dao;

import java.sql.ResultSet;

public interface ResultSetHandler<R> {

	R handle(ResultSet rs) throws SQLRuntimeException;
}
