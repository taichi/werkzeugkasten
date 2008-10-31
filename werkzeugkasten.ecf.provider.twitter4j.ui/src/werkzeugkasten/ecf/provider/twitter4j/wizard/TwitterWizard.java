package werkzeugkasten.ecf.provider.twitter4j.wizard;

import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.ContainerFactory;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.ui.IConnectWizard;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import werkzeugkasten.ecf.provider.twitter4j.Constants;

public class TwitterWizard extends Wizard implements IConnectWizard, INewWizard {

	protected TwitterWizardPage page;
	protected IContainer container;

	public TwitterWizard() {
		// nothing to do.
	}

	@Override
	public void init(IWorkbench workbench, IContainer container) {
		this.container = container;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		try {
			this.container = ContainerFactory.getDefault().createContainer(
					Constants.ID_PLUGIN);
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
		// TODO Auto-generated method stub
		return false;
	}

}
