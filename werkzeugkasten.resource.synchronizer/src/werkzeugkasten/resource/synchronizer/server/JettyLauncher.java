/*
 * Copyright 2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package werkzeugkasten.resource.synchronizer.server;

import java.io.File;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import werkzeugkasten.resource.synchronizer.Activator;
import werkzeugkasten.resource.synchronizer.servlet.ListServlet;
import werkzeugkasten.resource.synchronizer.servlet.RefreshServlet;
import werkzeugkasten.resource.synchronizer.servlet.SelectionServlet;
import werkzeugkasten.resource.synchronizer.servlet.SrcLocationServlet;

/**
 * 
 * @author taichi
 * 
 */
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
			root.addServlet(new ServletHolder(new SrcLocationServlet()),
					"/srcloc");
			root.addServlet(new ServletHolder(new SelectionServlet()),
					"/select");
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
		if (isRunning()) {
			try {
				this.server.stop();
			} catch (Exception e) {
				Activator.log(e);
			}
		}
	}
}
