package werkzeugkasten.mvncrawler;

import java.util.Map;
import java.util.Set;

import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.ArtifactBuilder;
import werkzeugkasten.mvnhack.repository.Configuration;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.impl.DefaultArtifact;
import werkzeugkasten.mvnhack.repository.impl.DefaultConfiguration;
import werkzeugkasten.mvnhack.repository.impl.DefaultContext;
import werkzeugkasten.mvnhack.repository.impl.RemoteRepository;
import werkzeugkasten.mvnhack.repository.impl.StAXArtifactBuilder;

public class Main {

	public static void main(String[] args) {
		new Main().execute();
	}

	public Main() {
	}

	public void execute() {
		String topUrl = "http://repo1.maven.org/maven2/org/slf4j/";
		ArtifactBuilder builder = new StAXArtifactBuilder() {
			@Override
			protected void reconcileDependencies(Context context,
					DefaultArtifact project, Set<Artifact> managed,
					Map<String, String> m) {
			}
		};
		Configuration config = new DefaultConfiguration();
		config.addRepository(new RemoteRepository(Constants.CENTRAL_REPOSITORY,
				builder));
		Context context = new DefaultContext(config) {
			@Override
			protected void resolveDependencies(Artifact a) {
			}
		};
		Eater eater = new Eater(context, builder);
		Waiter waiter = new Waiter(eater);
		try {
			waiter.begin();
			CrawlerContext c = new CrawlerContext(topUrl, waiter);
			c.crawlmore(topUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			waiter.finish();
		}
	}

}
