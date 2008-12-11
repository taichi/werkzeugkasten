package werkzeugkasten.twowaysql.dao.base;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import werkzeugkasten.twowaysql.dao.ResultSetHandler;
import werkzeugkasten.twowaysql.dao.SQLRuntimeException;
import werkzeugkasten.twowaysql.dao.TwoWaySqlContext;
import werkzeugkasten.twowaysql.dao.TwoWaySqlExecutor;

public class DataSourceTwoWaySqlExecutor implements TwoWaySqlExecutor {

	protected DataSource dataSource;

	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
	}

	protected Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	@Override
	public <EC, C extends TwoWaySqlContext<EC>> void execute(C context)
			throws SQLRuntimeException {

	}

	@Override
	public <EC, C extends TwoWaySqlContext<EC>, R> R execute(C context,
			ResultSetHandler<R> handler) throws SQLRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
