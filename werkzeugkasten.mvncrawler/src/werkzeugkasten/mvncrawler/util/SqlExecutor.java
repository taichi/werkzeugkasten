package werkzeugkasten.mvncrawler.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlExecutor {
	protected static final Logger LOG = LoggerFactory
			.getLogger(SqlExecutor.class);

	protected String connectionUrl;

	protected Properties properties;

	public SqlExecutor(String url, Properties p) {
		this.connectionUrl = url;
		this.properties = p;
	}

	public <V> V execute(Handler<V> h) {
		Connection con = null;
		try {
			con = getConnection();
			if (con != null) {
				PreparedStatement ps = con.prepareStatement(h.getSql());
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
			LOG.error(e.getMessage(), e);
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

	protected Connection getConnection() {
		try {
			return DriverManager.getConnection(this.connectionUrl, properties);
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public interface Handler<V> {
		String getSql();

		V execute(PreparedStatement ps) throws SQLException;
	}
}
