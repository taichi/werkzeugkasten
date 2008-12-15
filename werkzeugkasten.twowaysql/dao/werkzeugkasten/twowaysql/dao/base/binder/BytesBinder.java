package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class BytesBinder implements Binder {

	protected byte[] value;

	public BytesBinder(byte[] value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setBytes(index, value);
	}

}
