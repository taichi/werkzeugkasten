package werkzeugkasten.ecf.provider.twitter4j.wizard;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.ContainerFactory;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.security.ConnectContextFactory;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.ecf.presence.chatroom.IChatRoomManager;
import org.eclipse.ecf.ui.IConnectWizard;
import org.eclipse.ecf.ui.actions.AsynchContainerConnectAction;
import org.eclipse.ecf.ui.dialogs.IDCreateErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.progress.WorkbenchJob;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.ecf.provider.twitter4j.Constants;
import werkzeugkasten.ecf.provider.twitter4j.container.TwitterContainer;
import werkzeugkasten.ecf.provider.twitter4j.ui.TwitterUI;

public class TwitterWizard extends Wizard implements IConnectWizard, INewWizard {

	protected TwitterWizardPage page;
	protected TwitterContainer container;

	public TwitterWizard() {
		// nothing to do.
	}

	@Override
	public void init(IWorkbench workbench, IContainer container) {
		if (container instanceof TwitterContainer) {
			this.container = (TwitterContainer) container;
		}
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		try {
			IContainer c = ContainerFactory.getDefault().createContainer(
					Constants.ID_PLUGIN);
			if (c instanceof TwitterContainer) {
				this.container = (TwitterContainer) c;
			}
		} catch (ContainerCreateException e) {
			// nothing to do.
		}
	}

	@Override
	public void addPages() {
		super.addPages();
		this.page = new TwitterWizardPage();
		addPage(this.page);
	}

	@Override
	public boolean performFinish() {
		String uid = this.page.getUserId();
		String pas = this.page.getPassword();

		IConnectContext ctx = ConnectContextFactory
				.createPasswordConnectContext(pas);
		ID targetID = null;
		try {
			targetID = IDFactory.getDefault().createID(
					container.getConnectNamespace(), uid);
		} catch (final IDCreateException e) {
			new IDCreateErrorDialog(null, uid, e).open();
			return false;
		}

		final IChatRoomManager manager = AdaptableUtil.to(this.container,
				IChatRoomManager.class);
		if (manager != null) {
			page.saveInput();
			TwitterUI ui = new TwitterUI(this.container, manager);
			ui.showForTarget(targetID);
			if (!ui.isContainerConnected()) {
				new AsynchContainerConnectAction(container, targetID, ctx,
						null, new Runnable() {
							public void run() {
								new WorkbenchJob("") {
									@Override
									public IStatus runInUIThread(
											IProgressMonitor monitor) {
										container.readTimeline();
										schedule(3 * 60 * 1000);
										return Status.OK_STATUS;
									}
								}.schedule();
							}
						}).run();
				return true;
			}
		}
		return false;
	}
}
