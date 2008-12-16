package werkzeugkasten.twowaysql.dao.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.twowaysql.dao.el.mvel.MVELExpressionParser;
import werkzeugkasten.twowaysql.jdbc.ConnectionHandler;
import werkzeugkasten.twowaysql.jdbc.JdbcFunctors;
import werkzeugkasten.twowaysql.jdbc.StatementHandler;

public class DefaultSqlProcessorTest {

	protected static Server tcp;

	static final String url = "jdbc:h2:tcp://localhost:9092/test";

	@BeforeClass
	public static void bootDb() throws Exception {
		tcp = Server.createTcpServer(null);
		tcp.start();
		JdbcFunctors.handleConnection(new ConnectionHandler<Void>() {
			@Override
			public Connection getConnection() throws SQLException {
				org.h2.Driver.load();
				return DriverManager.getConnection(url, "sa", "");
			}

			@Override
			public Void handle(final Connection c) throws SQLException {
				new Streams.using<InputStream, Exception>(Exception.class) {

					@Override
					public InputStream open() throws Exception {
						ClassLoader cl = Thread.currentThread()
								.getContextClassLoader();
						return cl
								.getResourceAsStream("werkzeugkasten/twowaysql/dao/base/testdata.sql");
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

	}

	@Test
	public void testProcess() {
		String path = "werkzeugkasten/twowaysql/dao/base/testQuery.sql";
		Map<String, String> map = new HashMap<String, String>();
		map.put("aaa", "testtest");
		int act = target.process(path, map);
		assertEquals(1, act);
	}

	@Test
	public void testProcessMapping() {
		fail("Not yet implemented");
	}

}
