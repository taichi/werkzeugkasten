package werkzeugkasten.twowaysql.dao.base.binder;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import werkzeugkasten.twowaysql.dao.Binder;

public class BigDecimalBinder implements Binder {

	protected BigDecimal value;

	public BigDecimalBinder(BigDecimal value) {
		this.value = value;
	}

	@Override
	public void bind(PreparedStatement ps, int index) throws SQLException {
		ps.setBigDecimal(index, value);
	}

}
