package werkzeugkasten.weblauncher.tomcat.startup;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;

public class PortOverrideLifecycleListener implements LifecycleListener {

	public void lifecycleEvent(LifecycleEvent event) {
		if (Lifecycle.INIT_EVENT.equals(event.getType())) {
			String port = System.getProperty("weblauncher.port", "8080");
			if (port != null && port.matches("\\d*")) {
				Lifecycle l = event.getLifecycle();
				if (l instanceof Server) {
					Server server = (Server) l;
					for (Service service : server.findServices()) {
						Connector[] connectors = service.findConnectors();
						if (connectors != null && connectors.length == 1) {
							connectors[0].setPort(Integer.parseInt(port));
							break;
						}
					}
				}
			}
		}
	}

}
