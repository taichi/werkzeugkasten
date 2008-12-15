package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import werkzeugkasten.twowaysql.dao.Binder;

public class TimeBinder implements Binder {

	protected Time value;

	public TimeBinder(Time value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setTime(index, value);
	}

}
