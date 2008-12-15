package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class ArrayBinder implements Binder {

	protected Array value;

	public ArrayBinder(Array value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setArray(index, value);
	}

}
