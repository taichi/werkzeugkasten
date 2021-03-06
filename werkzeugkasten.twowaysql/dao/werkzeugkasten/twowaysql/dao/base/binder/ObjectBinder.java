package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class ObjectBinder implements Binder {

	protected Object value;

	public ObjectBinder(Object value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setObject(index, value);
	}

}
