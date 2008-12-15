package werkzeugkasten.twowaysql.dao.base.binder;

import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.twowaysql.dao.Binder;

public class CharacterStreamBinder implements Binder {

	protected Reader value;

	public CharacterStreamBinder(Reader value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		try {
			ps.setCharacterStream(index, value);
		} finally {
			Streams.close(value);
		}
	}

}
