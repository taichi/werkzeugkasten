package werkzeugkasten.weblauncher.action;

import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.jface.action.IAction;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.progress.WorkbenchJob;

import werkzeugkasten.common.action.EnablerAction;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.ui.WorkbenchUtil;
import werkzeugkasten.common.util.Streams;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.job.StartServerJob;
import werkzeugkasten.weblauncher.nls.Strings;
import werkzeugkasten.weblauncher.preferences.WebPreferences;

/**
 * @author taichi
 * 
 */
public class ViewOnServerAction extends EnablerAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seasar.weblauncher.action.ServerAction#checkEnabled()
	 */
	protected boolean checkEnabled() {
		IProject project = Activator.findCurrentProject();
		boolean is = false;
		if (project != null) {
			if (ProjectUtil.hasNature(project, Constants.ID_NATURE)) {
				WebPreferences pref = Activator.getPreferences(project);
				if (pref.checkServerWhenOpen()) {
					is = true;
				} else {
					ILaunch launch = Activator.getLaunch(project);
					is = launch != null && launch.canTerminate();
				}
			}
		}
		return is;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		try {
			IProject project = Activator.findCurrentProject();
			if (project != null) {
				run(action, project);
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seasar.eclipse.common.action.AbstractProjectAction#run(org.eclipse
	 * .jface.action.IAction, org.eclipse.core.resources.IProject)
	 */
	public void run(IAction action, IProject project) throws CoreException {
		if (checkEnabled() == false) {
			return;
		}
		final IResource resource = WorkbenchUtil
				.getCurrentSelectedResouce(IResource.class);
		if (resource == null) {
			return;
		}
		final WebPreferences pref = Activator.getPreferences(project);

		if (pref.checkServerWhenOpen()) {
			ILaunch launch = Activator.getLaunch(project);
			if (launch == null || launch.isTerminated()) {
				StartServerJob job = new StartServerJob(project) {
					public IStatus runInWorkspace(IProgressMonitor monitor)
							throws CoreException {
						IStatus status = super.runInWorkspace(monitor);
						Job open = new ConnectAndOpenJob(resource, pref, 0);
						open.schedule(4000L);
						return status;
					}
				};
				job.schedule();
			} else {
				open(resource, pref);
			}
		} else {
			open(resource, pref);
		}
	}

	private class ConnectAndOpenJob extends WorkbenchJob {
		private WebPreferences pref;

		private int count = 0;

		private IResource resource;

		public ConnectAndOpenJob(IResource resource, WebPreferences pref,
				int count) {
			super(Strings.MSG_CONNECT_SERVER);
			this.resource = resource;
			this.pref = pref;
			this.count = count;
		}

		public IStatus runInUIThread(IProgressMonitor monitor) {
			monitor.beginTask(Strings.MSG_CONNECT_SERVER, 3);
			InputStream in = null;
			try {
				URL url = createOpenUrl(resource, pref);
				if (url != null) {
					URLConnection con = url.openConnection();
					monitor.worked(1);
					monitor.setTaskName(Strings.MSG_WAIT_FOR_SERVER);
					con.connect();
					in = con.getInputStream();
					in.read();
					monitor.worked(1);
					monitor.setTaskName(NLS.bind(Strings.MSG_OPEN_URL, url));
					open(url, resource.getProject(), pref);
					monitor.worked(1);
				}
			} catch (ConnectException con) {
				if (count < 3) {
					ConnectAndOpenJob job = new ConnectAndOpenJob(resource,
							pref, ++count);
					job.schedule(1000L);
				} else {
					Activator.log(con);
				}
			} catch (Exception e) {
				Activator.log(e);
			} finally {
				Streams.close(in);
				monitor.done();
			}
			return Status.OK_STATUS;
		}
	}

	private static void open(IResource resource, WebPreferences pref) {
		try {
			open(createOpenUrl(resource, pref), resource.getProject(), pref);
		} catch (Exception e) {
			Activator.log(e);
		}
	}

	private static void open(URL url, IProject project, WebPreferences pref)
			throws Exception {
		if (url != null) {
			IWorkbenchBrowserSupport support = PlatformUI.getWorkbench()
					.getBrowserSupport();
			IWebBrowser browser = null;
			if (support.isInternalWebBrowserAvailable()
					&& pref.useInternalWebBrowser()) {
				int flag = IWorkbenchBrowserSupport.AS_EDITOR
						| IWorkbenchBrowserSupport.LOCATION_BAR
						| IWorkbenchBrowserSupport.NAVIGATION_BAR
						| IWorkbenchBrowserSupport.STATUS
						| IWorkbenchBrowserSupport.PERSISTENT;
				browser = support.createBrowser(flag, Constants.ID_BROWSER,
						null, null);
				Activator.entry(project, url);
			} else {
				browser = support.getExternalBrowser();
			}
			if (browser != null) {
				browser.openURL(url);
			}
		}
	}

	private static URL createOpenUrl(IResource resource, WebPreferences pref)
			throws Exception {
		IPath p = resource.getFullPath();
		IPath webRoot = new Path(pref.getBaseDir());
		if (webRoot.isPrefixOf(p)) {
			p = p.removeFirstSegments(webRoot.segmentCount());
			StringBuffer stb = new StringBuffer();
			stb.append("http://localhost:");
			stb.append(pref.getWebPortNo());

			String s = pref.getContextName();
			if (s != null && s.startsWith("/") == false) {
				s = "/" + s;
			}
			stb.append(new Path(s).append(p).toString());
			return new URL(stb.toString());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.seasar.weblauncher.action.ServerAction#init(org.eclipse.ui.
	 * IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		window.getPartService().addPartListener(new IPartListener2() {

			public void partActivated(IWorkbenchPartReference partRef) {
			}

			public void partBroughtToTop(IWorkbenchPartReference partRef) {
			}

			public void partClosed(IWorkbenchPartReference partRef) {
				if ("org.eclipse.ui.browser.editor".equals(partRef.getId())) {
					IWorkbenchPart part = partRef.getPart(false);
					if (part instanceof IEditorPart) {
						IEditorPart editor = (IEditorPart) part;
						IEditorInput input = editor.getEditorInput();
						if (input instanceof IPersistableElement) {
							IPersistableElement element = (IPersistableElement) input;
							IMemento memento = XMLMemento
									.createWriteRoot("root");
							// see. WebBrowserEditorInput
							element.saveState(memento);
							String url = memento.getString("url");
							Activator.exit(url);
						}
					}
				}
			}

			public void partDeactivated(IWorkbenchPartReference partRef) {
			}

			public void partHidden(IWorkbenchPartReference partRef) {
			}

			public void partInputChanged(IWorkbenchPartReference partRef) {
			}

			public void partOpened(IWorkbenchPartReference partRef) {
			}

			public void partVisible(IWorkbenchPartReference partRef) {
			}

		});

		super.init(window);
	}

}
