package werkzeugkasten.mvncrawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import werkzeugkasten.common.util.FileUtil;
import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvncrawler.util.SqlExecutor;
import werkzeugkasten.mvncrawler.util.SqlExecutor.Handler;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.impl.ArtifactUtil;
import werkzeugkasten.mvnhack.repository.impl.DefaultArtifact;

public class CrawlerDaoTest {

	static Server server;
	static File data;
	CrawlerDao target;

	@BeforeClass
	public static void bootDb() throws Exception {
		final ClassLoader cl = CrawlerDaoTest.class.getClassLoader();
		URL url = cl.getResource(".");
		String datapath = url.getPath() + "/data";
		data = new File(datapath);
		String s = " -tcp -tcpPort 9092 -baseDir " + datapath;
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
				return Streams.readText(UrlUtil.open(create));
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
		FileUtil.delete(data.getPath());
	}

	@Before
	public void setUp() throws Exception {
		Properties p = new Properties();
		p.setProperty("user", "sa");
		target = new CrawlerDao("jdbc:h2:tcp://localhost:9092/test", p);
	}

	@Test
	public void testEntry() {
		DefaultArtifact a = new DefaultArtifact() {
			public DefaultArtifact init() {
				setGroupId("ggid1");
				setArtifactId("aaid1");
				setVersion("vv1");
				return this;
			}
		}.init();
		Set<Artifact> ds = a.getDependencies();
		final int[] num = { 0 };
		for (int i = 0; i < 5; i++) {
			DefaultArtifact d = new DefaultArtifact() {
				public DefaultArtifact init() {
					num[0]++;
					setGroupId("ggid" + num[0]);
					setArtifactId("aaid" + num[0]);
					setVersion("vv" + num[0]);
					setOptional(num[0] % 2 == 1);
					return this;
				}
			}.init();
			ds.add(d);
		}
		target.entry(a);
		List<Artifact> list = target.selectDependencies(ArtifactUtil.create(
				"ggid1", "aaid1", "vv1"));
		assertNotNull(list);
		assertEquals(5, list.size());
		assertEquals("ggid1", list.get(0).getGroupId());
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
				URL data = getClass().getClassLoader().getResource(
						"testdata.sql");
				return Streams.readText(UrlUtil.open(data));
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
