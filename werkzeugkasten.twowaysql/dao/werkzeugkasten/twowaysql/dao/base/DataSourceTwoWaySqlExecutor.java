package werkzeugkasten.twowaysql.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.ConnectionHandler;
import werkzeugkasten.twowaysql.dao.JdbcFunctors;
import werkzeugkasten.twowaysql.dao.ResultSetHandler;
import werkzeugkasten.twowaysql.dao.SQLRuntimeException;
import werkzeugkasten.twowaysql.dao.StatementHandler;
import werkzeugkasten.twowaysql.dao.TwoWaySqlContext;
import werkzeugkasten.twowaysql.dao.TwoWaySqlExecutor;

public class DataSourceTwoWaySqlExecutor implements TwoWaySqlExecutor {

	static final Logger LOG = LoggerFactory.getLogger(TwoWaySqlExecutor.class);

	protected DataSource dataSource;

	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	@Override
	public <EC, C extends TwoWaySqlContext<EC>> Integer execute(final C context)
			throws SQLRuntimeException {
		return JdbcFunctors.execute(new ConnectionHandler<Integer>() {
			@Override
			public Connection getConnection() throws SQLException {
				return DataSourceTwoWaySqlExecutor.this.getConnection();
			}

			@Override
			public Integer handle(final Connection c) throws SQLException {
				return JdbcFunctors
						.execute(new StatementHandler<PreparedStatement, Integer>() {
							@Override
							public PreparedStatement prepare()
									throws SQLException {
								String sql = context.getSql();
								if (LOG.isDebugEnabled()) {
									LOG.debug(sql);
								}
								return c.prepareStatement(sql);
							}

							@Override
							public Integer handle(PreparedStatement statement)
									throws SQLException {
								int index = 1;
								for (Binder b : context.getBinders()) {
									b.bind(statement, index++);
								}
								return statement.executeUpdate();
							}
						});
			}
		});
	}

	@Override
	public <EC, C extends TwoWaySqlContext<EC>, R> R execute(final C context,
			final ResultSetHandler<R> handler) throws SQLRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
