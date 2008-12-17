package werkzeugkasten.twowaysql.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.ResultSetMapper;
import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.dao.SqlExecutor;
import werkzeugkasten.twowaysql.jdbc.ConnectionHandler;
import werkzeugkasten.twowaysql.jdbc.JdbcFunctors;
import werkzeugkasten.twowaysql.jdbc.ResultSetHandler;
import werkzeugkasten.twowaysql.jdbc.SQLRuntimeException;
import werkzeugkasten.twowaysql.jdbc.StatementHandler;

public class DataSourceSqlExecutor implements SqlExecutor {

	static final Logger LOG = LoggerFactory
			.getLogger(DataSourceSqlExecutor.class);

	protected DataSource dataSource;

	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
	}

	protected static abstract class PreparedSQLExecutor<EC, R> implements
			ConnectionHandler<R>, StatementHandler<PreparedStatement, R> {
		protected SqlContext<EC> context;
		protected DataSource ds;
		protected Connection c;

		public PreparedSQLExecutor(DataSource ds, SqlContext<EC> context) {
			this.ds = ds;
			this.context = context;
		}

		@Override
		public Connection getConnection() throws SQLException {
			this.c = this.ds.getConnection();
			return this.c;
		}

		@Override
		public R handle(Connection c) throws SQLException {
			return JdbcFunctors.handleStatement(this);
		}

		@Override
		public PreparedStatement prepare() throws SQLException {
			String sql = this.context.getSql();
			if (LOG.isDebugEnabled()) {
				LOG.debug(sql);
			}
			return this.c.prepareStatement(sql);
		}

		@Override
		public R handle(PreparedStatement statement) throws SQLException {
			int index = 1;
			for (Binder b : context.getBinders()) {
				b.bind(statement, index++);
			}
			return execute(statement);
		}

		protected abstract R execute(PreparedStatement ps) throws SQLException;
	}

	@Override
	public <EC, C extends SqlContext<EC>> Integer execute(final C context)
			throws SQLRuntimeException {
		return JdbcFunctors
				.handleConnection(new PreparedSQLExecutor<EC, Integer>(
						this.dataSource, context) {
					@Override
					protected Integer execute(PreparedStatement ps)
							throws SQLException {
						return ps.executeUpdate();
					};
				});
	}

	@Override
	public <EC, C extends SqlContext<EC>, R> R execute(final C context,
			final ResultSetMapper<R> rsm) throws SQLRuntimeException {
		return JdbcFunctors.handleConnection(new PreparedSQLExecutor<EC, R>(
				this.dataSource, context) {
			@Override
			protected R execute(final PreparedStatement ps) throws SQLException {
				return JdbcFunctors.handleResultSet(new ResultSetHandler<R>() {
					@Override
					public ResultSet executeQuery() throws SQLException {
						return ps.executeQuery();
					}

					@Override
					public R handle(ResultSet rs) throws SQLException {
						return rsm.map(rs);
					}
				});
			};
		});
	}
}
