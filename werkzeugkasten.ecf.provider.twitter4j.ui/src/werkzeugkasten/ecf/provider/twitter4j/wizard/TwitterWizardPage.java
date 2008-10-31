package werkzeugkasten.ecf.provider.twitter4j.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class TwitterWizardPage extends WizardPage {

	public static final String NAME = TwitterWizardPage.class.getName();

	protected TwitterWizardPage() {
		super(NAME);
	}

	@Override
	public void createControl(Composite parent) {

	}

	public String getUserId() {
		return null; // TODO not implement yet
	}

	public String getPassword() {
		return null; // TODO not implement yet
	}

}
