package werkzeugkasten.twowaysql.dao.base;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.twowaysql.dao.ResultSetMapper;
import werkzeugkasten.twowaysql.dao.el.mvel.MVELExpressionParser;
import werkzeugkasten.twowaysql.jdbc.ConnectionHandler;
import werkzeugkasten.twowaysql.jdbc.JdbcFunctors;
import werkzeugkasten.twowaysql.jdbc.StatementHandler;

public class DefaultSqlProcessorTest {

	protected static Server tcp;

	static final String url = "jdbc:h2:tcp://localhost:9092/test";

	@BeforeClass
	public static void bootDb() throws Exception {
		org.h2.Driver.load();
		tcp = Server.createTcpServer(null);
		tcp.start();
		executeSql("werkzeugkasten/twowaysql/dao/base/testdata.sql");
	}

	private static void executeSql(final String path) {
		JdbcFunctors.handleConnection(new ConnectionHandler<Void>() {
			@Override
			public Connection getConnection() throws SQLException {
				return DriverManager.getConnection(url, "sa", "");
			}

			@Override
			public Void handle(final Connection c) throws SQLException {
				new Streams.using<InputStream, Exception>(Exception.class) {

					@Override
					public InputStream open() throws Exception {
						ClassLoader cl = Thread.currentThread()
								.getContextClassLoader();
						return cl.getResourceAsStream(path);
					}

					@Override
					public void handle(InputStream stream) throws Exception {
						final String sql = Streams.readText(stream);
						JdbcFunctors
								.handleStatement(new StatementHandler<Statement, Void>() {
									@Override
									public Statement prepare()
											throws SQLException {
										return c.createStatement();
									}

									@Override
									public Void handle(Statement statement)
											throws SQLException {
										statement.execute(sql);
										return null;
									}
								});
					}

					@Override
					public void happen(Exception exception) {
						exception.printStackTrace();
					}
				};
				return null;
			}
		});
	}

	@AfterClass
	public static void exitDb() throws Exception {
		tcp.stop();
	}

	DefaultSqlProcessor target;

	@Before
	public void setUp() throws Exception {
		DefaultSqlEnviroment env = new DefaultSqlEnviroment();
		DefaultQueryLoader loader = new DefaultQueryLoader();
		env.setLoader(loader);
		DefaultBinderProducer pro = new DefaultBinderProducer();
		pro.initialize();
		env.setBinderProducer(pro);
		env.setELParser(new MVELExpressionParser());
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL(url);
		ds.setUser("sa");
		DataSourceSqlExecutor exec = new DataSourceSqlExecutor();
		exec.setDataSource(ds);
		env.setExecutor(exec);

		target = new DefaultSqlProcessor();
		target.setEnviroment(env);
		executeSql("werkzeugkasten/twowaysql/dao/base/initdata.sql");

	}

	@After
	public void tearDown() throws Exception {
		executeSql("werkzeugkasten/twowaysql/dao/base/destroydata.sql");
	}

	@Test
	public void testProcess() {
		String path = "werkzeugkasten/twowaysql/dao/base/testInsertQuery.sql";
		Map<String, String> map = new HashMap<String, String>();
		map.put("aaa", "testtest");
		int act = target.process(path, map);
		assertEquals(1, act);
	}

	@Test
	public void testProcessMapping() {
		String path = "werkzeugkasten/twowaysql/dao/base/testSelectQuery.sql";
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("aaa", 2);
		String s = target.process(path, map, new ResultSetMapper<String>() {
			@Override
			public String map(ResultSet rs) throws SQLException {
				return rs.next() ? rs.getString(2) : null;
			}
		});
		assertEquals("World", s);
	}

	@Test
	public void testBeginContains() throws Exception {
		String path = "werkzeugkasten/twowaysql/dao/base/testBeginQuery.sql";
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("aaa", 1);
		int count = target.process(path, map, new ResultSetMapper<Integer>() {
			@Override
			public Integer map(ResultSet rs) throws SQLException {
				int count = 0;
				while (rs.next()) {
					count++;
				}
				return count;
			}
		});
		assertEquals(2, count);
	}
}
