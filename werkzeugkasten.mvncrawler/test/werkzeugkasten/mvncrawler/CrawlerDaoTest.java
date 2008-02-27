package werkzeugkasten.mvncrawler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
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
		final ClassLoader cl = CrawlerDaoTest.class.getClassLoader();
		URL url = cl.getResource(".");
		String s = " -tcp -tcpPort 9092 -baseDir " + url.getPath();
		String[] args = s.split(" ");
		server = Server.createTcpServer(args);
		server.start();
		Properties p = new Properties();
		p.setProperty("user", "sa");
		SqlExecutor se = new SqlExecutor("jdbc:h2:tcp://localhost:9092/test", p);
		se.execute(new Handler<Void>() {
			@Override
			public String getSql() {
				URL create = cl.getResource("createTable.sql");
				return StreamUtil.readText(UrlUtil.open(create));
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

	@Test
	public void testSelectDependencies() {
		Properties p = new Properties();
		p.setProperty("user", "sa");
		SqlExecutor se = new SqlExecutor("jdbc:h2:tcp://localhost:9092/test", p);
		se.execute(new Handler<Void>() {
			@Override
			public String getSql() {
				URL data = getClass().getClassLoader().getResource("testdata.sql");
				return StreamUtil.readText(UrlUtil.open(data));
			}
			@Override
			public Void execute(PreparedStatement ps) throws SQLException {
				ps.executeUpdate();
				return null;
			}
		});
		Artifact a = ArtifactUtil.create("gid1", "aid1", "v1");
		List<Artifact> list = target.selectDependencies(a);
		assertNotNull(list);
		assertEquals(2, list.size());
		assertEquals("gid2", list.get(0).getGroupId());
		assertEquals("gid3", list.get(1).getGroupId());
	}

}
