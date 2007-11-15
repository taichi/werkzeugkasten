package werkzeugkasten.weblauncher.jetty;

import java.io.File;
import java.util.HashMap;

import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.jetty.deployer.ConfigurationManager;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.log.Log;
import org.mortbay.xml.XmlConfiguration;

@SuppressWarnings("unchecked")
public class ContextFileDeployer extends AbstractLifeCycle {

	private String contextfile;
	private ContextHandler current;
	private ContextHandlerCollection _contexts;
	private ConfigurationManager _configMgr;

	public void setContextfile(String contextfile) {
		this.contextfile = contextfile;
	}

	public String getContextfile() {
		return contextfile;
	}

	/* ------------------------------------------------------------ */
	/**
	 * @return the ContextHandlerColletion to which to deploy the contexts
	 */
	public ContextHandlerCollection getContexts() {
		return _contexts;
	}

	/* ------------------------------------------------------------ */
	/**
	 * Associate with a {@link ContextHandlerCollection}.
	 * 
	 * @param contexts
	 *            the ContextHandlerColletion to which to deploy the contexts
	 */
	public void setContexts(ContextHandlerCollection contexts) {
		if (isStarted() || isStarting())
			throw new IllegalStateException(
					"Cannot set Contexts after deployer start");
		_contexts = contexts;
	}

	/* ------------------------------------------------------------ */
	/**
	 * @param configMgr
	 */
	public void setConfigurationManager(ConfigurationManager configMgr) {
		_configMgr = configMgr;
	}

	/* ------------------------------------------------------------ */
	/**
	 * @return
	 */
	public ConfigurationManager getConfigurationManager() {
		return _configMgr;
	}

	/* ------------------------------------------------------------ */
	private void deploy(String filename) throws Exception {
		ContextHandler context = createContext(filename);
		Log.info("Deploy " + filename + " -> " + context);
		_contexts.addHandler(context);
		if (_contexts.isStarted()) {
			context.start();
		}
		this.current = context;
	}

	/* ------------------------------------------------------------ */
	private void undeploy(String filename) throws Exception {
		ContextHandler context = this.current;
		Log.info("Undeploy " + filename + " -> " + context);
		if (context == null)
			return;
		context.stop();
		_contexts.removeHandler(context);
		this.current = null;
	}

	/* ------------------------------------------------------------ */
	/**
	 * Create a WebAppContext for the webapp being hot deployed, then apply the
	 * xml config file to it to configure it.
	 * 
	 * @param filename
	 *            the config file found in the hot deploy directory
	 * @return
	 * @throws Exception
	 */
	private ContextHandler createContext(String filename) throws Exception {
		// The config file can call any method on WebAppContext to configure
		// the webapp being deployed.
		File hotDeployXmlFile = new File(filename);
		if (!hotDeployXmlFile.exists())
			return null;

		XmlConfiguration xmlConfiguration = new XmlConfiguration(
				hotDeployXmlFile.toURL());
		HashMap properties = new HashMap();
		properties.put("Server", _contexts.getServer());
		if (_configMgr != null)
			properties.putAll(_configMgr.getProperties());

		xmlConfiguration.setProperties(properties);
		ContextHandler context = (ContextHandler) xmlConfiguration.configure();
		return context;
	}

	@Override
	protected void doStart() throws Exception {
		if (_contexts == null) {
			throw new IllegalStateException(
					"No context handler collection specified for deployer");
		}
		if (contextfile == null || contextfile.length() < 1
				|| contextfile.endsWith("xml") == false) {
			throw new IllegalStateException(
					"No contextfile specified for deployer");
		}
		deploy(contextfile);
	}

	@Override
	protected void doStop() throws Exception {
		undeploy(contextfile);
	}
}
