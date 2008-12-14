package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class ObjectBinder implements Binder {

	protected Object o;

	public ObjectBinder(Object o) {
		this.o = o;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setObject(index, this.o);
	}

}
