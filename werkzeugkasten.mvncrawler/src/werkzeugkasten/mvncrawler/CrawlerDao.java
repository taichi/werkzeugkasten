package werkzeugkasten.mvncrawler;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvncrawler.util.SqlExecutor;
import werkzeugkasten.mvncrawler.util.SqlExecutor.Handler;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.impl.DefaultArtifact;

public class CrawlerDao {

	protected static final Logger LOG = LoggerFactory
			.getLogger(CrawlerDao.class);

	protected static final String SELECT_DEPENDENCY;

	static {
		ClassLoader cl = CrawlerDao.class.getClassLoader();
		URL url = cl
				.getResource("werkzeugkasten/mvncrawler/CrawlerDao_selectDependencies.sql");
		SELECT_DEPENDENCY = Streams.readText(UrlUtil.open(url));
	}

	protected SqlExecutor executor;

	public CrawlerDao(String url, Properties p) {
		this.executor = new SqlExecutor(url, p);
	}

	public void entry(Artifact a) {
		Long id = selectId(a);
		if (id == null) {
			id = insert(a);
			for (Artifact d : a.getDependencies()) {
				Long onId = selectId(d);
				if (onId == null) {
					onId = insert(d);
				}
				if (onId != null) {
					insert(id, onId, d.isOptional());
				}
			}
		}
	}

	protected Long insert(final Artifact a) {
		return executor.execute(new Handler<Long>() {
			@Override
			public String getSql() {
				return "INSERT INTO ARTIFACT (GROUP_ID,ARTIFACT_ID,VERSION) VALUES (?,?,?)";
			}

			@Override
			public Long execute(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, a.getGroupId());
				ps.setString(i++, a.getArtifactId());
				ps.setString(i++, a.getVersion());
				if (0 < ps.executeUpdate()) {
					return selectId(a);
				}
				return null;
			}
		});
	}

	protected void insert(final Long fromId, final Long toId,
			final boolean isOptional) {
		executor.execute(new Handler<Void>() {
			@Override
			public String getSql() {
				return "INSERT INTO DEPENDENCY (FROM_ARTIFACT_ID,TO_ARTIFACT_ID,OPTIONAL) VALUES (?,?,?)";
			}

			@Override
			public Void execute(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setLong(i++, fromId);
				ps.setLong(i++, toId);
				ps.setBoolean(i++, isOptional);
				ps.execute();
				return null;
			}
		});
	}

	protected Long selectId(final Artifact a) {
		return executor.execute(new Handler<Long>() {
			@Override
			public String getSql() {
				return "SELECT ID FROM ARTIFACT WHERE GROUP_ID = ? AND ARTIFACT_ID = ? AND VERSION = ?";
			}

			@Override
			public Long execute(PreparedStatement ps) throws SQLException {
				int i = 1;
				ps.setString(i++, a.getGroupId());
				ps.setString(i++, a.getArtifactId());
				ps.setString(i++, a.getVersion());
				ResultSet rs = ps.executeQuery();
				try {
					if (rs.next()) {
						return rs.getLong("ID");
					} else {
						return null;
					}
				} finally {
					rs.close();
				}
			}
		});
	}

	public List<Artifact> selectDependencies(final Artifact a) {
		final Long id = selectId(a);
		if (id != null) {
			return executor.execute(new Handler<List<Artifact>>() {
				@Override
				public String getSql() {
					return SELECT_DEPENDENCY;
				}

				@Override
				public List<Artifact> execute(PreparedStatement ps)
						throws SQLException {
					int i = 1;
					ps.setString(i++, a.getGroupId());
					ps.setString(i++, a.getArtifactId());
					ps.setString(i++, a.getVersion());
					ResultSet rs = ps.executeQuery();
					try {
						List<Artifact> list = new ArrayList<Artifact>();
						while (rs.next()) {
							Artifact a = new DefaultArtifact() {
								public Artifact init(ResultSet rs)
										throws SQLException {
									setGroupId(rs.getString("GROUP_ID"));
									setArtifactId(rs.getString("ARTIFACT_ID"));
									setVersion(rs.getString("VERSION"));
									setOptional(rs.getBoolean("OPTIONAL"));
									return this;
								}
							}.init(rs);
							list.add(a);
						}
						return list;
					} finally {
						rs.close();
					}
				}
			});
		}
		return null;
	}
}
