package werkzeugkasten.twowaysql.dao.base.binder;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class BinaryStreamBinder implements Binder {

	protected InputStream value;

	public BinaryStreamBinder(InputStream value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setBinaryStream(index, value);
	}

}
