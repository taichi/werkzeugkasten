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
			String port = System.getProperty("weblauncher.port");
			SDLoader server = new SDLoader(Integer.parseInt(port)) {
				protected void initWebApp() {
					try {
						log.info("WebApplication initialize start.");
						webAppManager = new EnhancedWebAppManager();
						webAppManager.init(config);
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
		protected void detectWebApps() throws Exception {
			String contextfile = System.getProperty("weblauncher.ctx.loc");
			if (contextfile == null || contextfile.length() < 1) {
				throw new IllegalArgumentException(
						"context file path must be set.");
			}
			log.info(contextfile);
			File file = new File(contextfile);
			if (file.exists()) {
				parseContextXMLs(new File[] { file });
			}else{
				throw new IllegalArgumentException(
				"context file not found.file="+contextfile);
			}
		}
	}
}
