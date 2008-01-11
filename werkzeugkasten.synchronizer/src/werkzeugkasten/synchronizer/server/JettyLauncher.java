package werkzeugkasten.synchronizer.server;

import java.io.File;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import werkzeugkasten.synchronizer.Activator;
import werkzeugkasten.synchronizer.servlet.ListServlet;
import werkzeugkasten.synchronizer.servlet.RefreshServlet;
import werkzeugkasten.synchronizer.servlet.SrcLocationServlet;

public class JettyLauncher {

	public static final int DEFAULT_HTTP_PORT = 8386;

	protected File workDir;

	protected Server server = null;

	public JettyLauncher(File workDir) {
		this.workDir = workDir;
	}

	public void start() {
		if (this.server == null || this.server.isRunning() == false) {
			this.server = new Server(DEFAULT_HTTP_PORT);
			ContextHandlerCollection contexts = new ContextHandlerCollection();
			server.setHandler(contexts);
			Context root = new Context(contexts, "/", Context.NO_SESSIONS);
			ServletHolder refresh = new ServletHolder(new RefreshServlet());
			root.addServlet(refresh, "/");
			root.addServlet(refresh, "/refresh");
			root.addServlet(new ServletHolder(new ListServlet()), "/list");
			root
					.addServlet(new ServletHolder(new SrcLocationServlet()),
							"/src");
			try {
				server.start();
			} catch (Exception e) {
				Activator.log(e);
			}
		}
	}

	public boolean isRunning() {
		return this.server != null && this.server.isRunning();
	}

	public void stop() {
		if (this.server != null && this.server.isRunning()) {
			try {
				this.server.stop();
			} catch (Exception e) {
				Activator.log(e);
			}
		}
	}
}
