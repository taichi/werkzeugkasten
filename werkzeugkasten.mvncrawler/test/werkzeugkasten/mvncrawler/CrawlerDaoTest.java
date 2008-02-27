package werkzeugkasten.mvncrawler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvncrawler.util.SqlExecutor;
import werkzeugkasten.mvncrawler.util.SqlExecutor.Handler;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.impl.ArtifactUtil;

public class CrawlerDaoTest {

	static Server server;
	CrawlerDao target;

	@BeforeClass
	public static void bootDb() throws Exception {
		ClassLoader cl = CrawlerDaoTest.class.getClassLoader();
		URL url = cl.getResource(".");
		String s = " -tcp -tcpPort 9092 -baseDir " + url.getPath();
		String[] args = s.split(" ");
		server = Server.createTcpServer(args);
		server.start();
		URL create = cl.getResource("createTable.sql");
		final StringBuilder stb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(UrlUtil.open(create)));
			while (br.ready()) {
				stb.append(br.readLine());
			}
			Properties p = new Properties();
			p.setProperty("user", "sa");
			SqlExecutor se = new SqlExecutor(
					"jdbc:h2:tcp://localhost:9092/test", p);
			se.execute(new Handler<Void>() {
				@Override
				public String getSql() {
					return stb.toString();
				}

				@Override
				public Void execute(PreparedStatement ps) throws SQLException {
					ps.execute();
					return null;
				}
			});
			se.execute(new Handler<Void>() {
				@Override
				public String getSql() {
					return "DELETE FROM ARTIFACT;DELETE FROM DEPENDENCY;";
				}

				@Override
				public Void execute(PreparedStatement ps) throws SQLException {
					ps.executeUpdate();
					return null;
				}
			});

		} finally {
			StreamUtil.close(br);
		}
	}

	@AfterClass
	public static void stopDb() throws Exception {
		server.shutdown();
	}

	@Before
	public void setUp() throws Exception {
		Properties p = new Properties();
		p.setProperty("user", "sa");
		target = new CrawlerDao("jdbc:h2:tcp://localhost:9092/test", p);
	}

	@Ignore
	@Test
	public void testEntry() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertArtifact() {
		Artifact first = ArtifactUtil.create("group", "artifact", "1.0.1");
		Long id = target.insert(first);
		assertNotNull(id);
		target.insert(id, id, true);
	}

	@Ignore
	@Test
	public void testSelectDependencies() {
		fail("Not yet implemented");
	}

}
