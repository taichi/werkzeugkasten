package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class NClobBinder implements Binder {

	protected NClob value;

	public NClobBinder(NClob value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setNClob(index, value);
	}

}
