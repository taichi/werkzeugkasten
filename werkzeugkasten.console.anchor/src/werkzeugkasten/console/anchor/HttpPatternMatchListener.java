package werkzeugkasten.console.anchor;

import java.net.URL;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class HttpPatternMatchListener implements IPatternMatchListenerDelegate {

	protected TextConsole console;

	@Override
	public void connect(TextConsole console) {
		this.console = console;
	}

	@Override
	public void disconnect() {
		this.console = null;
	}

	@Override
	public void matchFound(PatternMatchEvent event) {
		try {
			IDocument doc = this.console.getDocument();
			String s = doc.get(event.getOffset(), event.getLength());
			URL url = new URL(s);
			this.console.addHyperlink(new URLHyperLink(url), event.getOffset(),
					event.getLength());
		} catch (Exception e) {
			Activator.log(e);
		}
	}

	class URLHyperLink implements IHyperlink {

		URL url;

		public URLHyperLink(URL url) {
			this.url = url;
		}

		@Override
		public void linkActivated() {
			try {
				IWorkbenchBrowserSupport support = PlatformUI.getWorkbench()
						.getBrowserSupport();
				IWebBrowser browser = null;
				if (support.isInternalWebBrowserAvailable()) {
					int flag = IWorkbenchBrowserSupport.AS_EDITOR
							| IWorkbenchBrowserSupport.LOCATION_BAR
							| IWorkbenchBrowserSupport.NAVIGATION_BAR
							| IWorkbenchBrowserSupport.STATUS
							| IWorkbenchBrowserSupport.PERSISTENT;
					browser = support.createBrowser(flag, Activator.PLUGIN_ID,
							null, null);
				} else {
					browser = support.getExternalBrowser();
				}
				if (browser != null) {
					browser.openURL(this.url);
				}
			} catch (Exception e) {
				Activator.log(e);
			}
		}

		@Override
		public void linkEntered() {
		}

		@Override
		public void linkExited() {
		}
	}
}
