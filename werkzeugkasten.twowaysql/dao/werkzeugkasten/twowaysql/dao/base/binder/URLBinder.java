package werkzeugkasten.twowaysql.dao.base.binder;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class URLBinder implements Binder {

	protected URL value;

	public URLBinder(URL value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setURL(index, value);
	}

}
