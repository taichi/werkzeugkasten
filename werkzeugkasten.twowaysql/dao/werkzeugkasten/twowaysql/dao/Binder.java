package werkzeugkasten.twowaysql.dao;

import java.sql.PreparedStatement;

import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;

public interface Binder {

	void bind(PreparedStatement ps, int index) throws SQLRuntimeException;
}
