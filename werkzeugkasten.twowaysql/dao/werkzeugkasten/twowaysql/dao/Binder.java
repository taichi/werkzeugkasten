package werkzeugkasten.twowaysql.dao;

import java.sql.PreparedStatement;

public interface Binder {

	void bind(PreparedStatement ps, int index) throws SQLRuntimeException;
}
