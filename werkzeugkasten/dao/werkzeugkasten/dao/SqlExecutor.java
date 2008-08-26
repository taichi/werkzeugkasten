package werkzeugkasten.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class SqlExecutor {

	DataSource dataSource;

	public <V> V execute(Handler<V> h) {
		Connection con = null;
		try {
			con = getConnection();
			if (con != null) {
				String sql = h.getSql();
				PreparedStatement ps = con.prepareStatement(sql);
				try {
					V result = h.execute(ps);
					con.commit();
					return result;
				} catch (RuntimeException e) {
					con.rollback();
					throw e;
				} catch (SQLException e) {
					con.rollback();
					throw e;
				} finally {
					ps.close();
				}
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return null;
	}

	protected Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public interface Handler<V> {
		String getSql();

		V execute(PreparedStatement ps) throws SQLException;
	}

	public abstract static class StatementHandler<V> implements Handler<V> {

		@Override
		public V execute(PreparedStatement ps) throws SQLException {
			ResultSet rs = null;
			try {
				bind(ps);
				rs = ps.executeQuery();
				return handle(rs);
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		}

		public abstract void bind(PreparedStatement ps) throws SQLException;

		public abstract V handle(ResultSet rs) throws SQLException;
	}

	public abstract static class ListResultSetHandler<V> extends
			StatementHandler<List<V>> {
		@Override
		public List<V> handle(ResultSet rs) throws SQLException {
			List<V> list = new ArrayList<V>();
			while (rs.next()) {
				list.add(map(rs));
			}
			return list;
		}

		public abstract V map(ResultSet rs) throws SQLException;
	}

}
