package werkzeugkasten.twowaysql.dao.base.binder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLXML;

import werkzeugkasten.twowaysql.dao.Binder;

public class SQLXMLBinder implements Binder {

	protected SQLXML value;

	public SQLXMLBinder(SQLXML value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setSQLXML(index, value);
	}

}
