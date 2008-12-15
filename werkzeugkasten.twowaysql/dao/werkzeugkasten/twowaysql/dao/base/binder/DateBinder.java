package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class DateBinder implements Binder {

	protected Date value;

	public DateBinder(Date value) {
		this.value = value;
	}

	public DateBinder(java.util.Date value) {
		this(new Date(value.getTime()));
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setDate(index, value);
	}

}
