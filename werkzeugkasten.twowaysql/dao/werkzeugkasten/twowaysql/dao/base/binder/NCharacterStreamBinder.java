package werkzeugkasten.twowaysql.dao.base.binder;

import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.twowaysql.dao.Binder;

public class NCharacterStreamBinder implements Binder {

	protected Reader value;

	public NCharacterStreamBinder(Reader value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		try {
			ps.setNCharacterStream(index, value);
		} finally {
			Streams.close(value);
		}
	}

}
