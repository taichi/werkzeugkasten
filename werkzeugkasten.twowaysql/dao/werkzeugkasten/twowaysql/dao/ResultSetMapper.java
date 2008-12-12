package werkzeugkasten.twowaysql.dao;

import java.sql.ResultSet;

import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;

public interface ResultSetMapper<R> {

	R map(ResultSet rs) throws SQLRuntimeException;
}
