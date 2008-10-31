package werkzeugkasten.mvncrawler;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvncrawler.util.SqlExecutor;
import werkzeugkasten.mvncrawler.util.SqlExecutor.Handler;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.ArtifactBuilder;
import werkzeugkasten.mvnhack.repository.Configuration;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.impl.DefaultConfiguration;
import werkzeugkasten.mvnhack.repository.impl.DefaultContext;
import werkzeugkasten.mvnhack.repository.impl.RemoteRepository;
import werkzeugkasten.mvnhack.repository.impl.StAXArtifactBuilder;

public class Main {
	protected static final String JDBC_URL = "jdbc:h2:file:./data/repository";

	public static void main(String[] args) {
		new Main().execute();
	}

	public Main() {
	}

	public void execute() {
		Properties p = new Properties();
		p.setProperty("user", "sa");
		setUpDatabase(p);

		String topUrl = "http://repo1.maven.org/maven2/";
		Waiter waiter = createWaiter(p);
		try {
			CrawlerContext c = new CrawlerContext(topUrl, waiter);
			c.crawlmore(topUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			waiter.dispose();
		}
	}

	protected Waiter createWaiter(Properties p) {
		ArtifactBuilder builder = new StAXArtifactBuilder();
		Context context = createContext(builder);
		CrawlerDao dao = new CrawlerDao(JDBC_URL, p);
		Eater eater = new Eater(context, builder, dao);
		Waiter waiter = new Waiter(eater);
		return waiter;
	}

	protected Context createContext(ArtifactBuilder builder) {
		Configuration config = new DefaultConfiguration();
		config.addRepository(new RemoteRepository(Constants.CENTRAL_REPOSITORY,
				builder));
		Context context = new DefaultContext(config) {
			@Override
			protected void resolveDependencies(Artifact a) {
			}
		};
		return context;
	}

	protected void setUpDatabase(Properties p) {
		final ClassLoader cl = getClass().getClassLoader();
		SqlExecutor se = new SqlExecutor(JDBC_URL, p);
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
	}

}
