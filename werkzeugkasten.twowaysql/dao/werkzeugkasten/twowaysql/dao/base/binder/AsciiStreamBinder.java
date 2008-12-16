package werkzeugkasten.twowaysql.dao.base.binder;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.twowaysql.dao.Binder;

public class AsciiStreamBinder implements Binder {

	protected InputStream value;

	public AsciiStreamBinder(InputStream value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		try {
			ps.setAsciiStream(index, value);
		} finally {
			Streams.close(value);
		}
	}

}
