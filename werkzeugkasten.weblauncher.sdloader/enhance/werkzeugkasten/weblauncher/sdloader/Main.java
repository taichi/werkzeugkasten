package werkzeugkasten.weblauncher.sdloader;

import java.io.File;

import sdloader.SDLoader;
import sdloader.javaee.WebAppManager;
import sdloader.log.SDLoaderLog;
import sdloader.log.SDLoaderLogFactory;

public class Main {

	private static final SDLoaderLog log = SDLoaderLogFactory
			.getLog(Main.class);

	public static void main(String[] args) {
		try {
			String port = System.getProperty("dblauncher.port");
			SDLoader server = new SDLoader(Integer.parseInt(port)) {
				protected void initWebApp() {
					try {
						log.info("WebApplication initialize start.");
						webAppManager = new EnhancedWebAppManager(this);
						webAppManager.init();
						log.info("WebApplication initialize success.");
					} catch (RuntimeException e) {
						log.error("WebApplication initialize fail.", e);
						throw e;
					}
				}
			};
			server.start();
		} catch (Throwable e) {
			log.error("SDLoader catch error.", e);
		}
	}

	public static class EnhancedWebAppManager extends WebAppManager {
		public EnhancedWebAppManager(SDLoader server) {
			super(server);
		}
		protected void detectWebApps() throws Exception {
			String contextfile = System.getProperty("dblauncher.ctx.loc");
			if (contextfile == null || contextfile.length() < 1) {
				throw new IllegalArgumentException(
						"context file path must be set.");
			}
			File file = new File(contextfile);
			if (file.exists()) {
				parseContextXMLs(new File[] { file });
			}
		}
	}
}
