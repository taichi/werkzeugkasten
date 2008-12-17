package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class IntBinder implements Binder {

	protected Integer value;

	public IntBinder(Integer value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setInt(index, value);
	}

}
