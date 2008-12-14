package werkzeugkasten.twowaysql.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Binder {

	void bind(PreparedStatement ps, int index) throws SQLException;
}
