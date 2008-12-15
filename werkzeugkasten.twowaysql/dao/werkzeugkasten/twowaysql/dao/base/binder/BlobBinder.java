package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class BlobBinder implements Binder {

	protected Blob value;

	public BlobBinder(Blob value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setBlob(index, value);
	}

}
