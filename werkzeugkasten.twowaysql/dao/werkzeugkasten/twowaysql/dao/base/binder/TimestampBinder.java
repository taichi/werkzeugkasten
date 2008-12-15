package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import werkzeugkasten.twowaysql.dao.Binder;

public class TimestampBinder implements Binder {

	protected Timestamp value;

	public TimestampBinder(Timestamp value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setTimestamp(index, value);
	}

}
