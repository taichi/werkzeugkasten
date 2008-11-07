package werkzeugkasten.ecf.provider.twitter4j.wizard;

import java.io.IOException;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.ecf.provider.twitter4j.ui.Activator;

public class TwitterWizardPage extends WizardPage {

	public static final String NAME = TwitterWizardPage.class.getName();

	protected Text userId;
	protected Text passwordText;

	protected TwitterWizardPage() {
		super(NAME);
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		ModifyListener verifier = new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String uid = userId.getText();
				String pas = passwordText.getText();
				String[] ary = { uid, pas };
				for (String s : ary) {
					if (StringUtil.isEmpty(s)) {
						setPageComplete(false);
						return;
					}
				}
				setPageComplete(true);
			}
		};

		this.userId = createPart(composite, "twitter id :");
		this.userId.addModifyListener(verifier);
		this.passwordText = createPart(composite, "password :", SWT.SINGLE
				| SWT.BORDER | SWT.PASSWORD);
		this.passwordText.addModifyListener(verifier);

		restoreInput();
		org.eclipse.jface.dialogs.Dialog.applyDialogFont(composite);
		setControl(composite);
	}

	private Text createPart(Composite composite, String label) {
		return createPart(composite, label, SWT.SINGLE | SWT.BORDER);
	}

	private Text createPart(Composite composite, String label, int style) {
		Label l = new Label(composite, SWT.NONE);
		l.setText(label);
		Text txt = new Text(composite, style);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		txt.setLayoutData(data);
		return txt;
	}

	public String getUserId() {
		return this.userId.getText();
	}

	public String getPassword() {
		return this.passwordText.getText();
	}

	protected void restoreInput() {
		ScopedPreferenceStore store = new ScopedPreferenceStore(
				new ConfigurationScope(), Activator.PLUGIN_ID);
		String uid = store.getString("userid");
		if (StringUtil.isEmpty(uid) == false) {
			this.userId.setText(uid);
			restorePassword(uid);
		}
	}

	protected void restorePassword(String username) {
		String pw = Activator.retrievePassword(username);
		if (pw != null) {
			this.passwordText.setText(pw);
		}
	}

	protected void saveInput() {
		ScopedPreferenceStore store = new ScopedPreferenceStore(
				new ConfigurationScope(), Activator.PLUGIN_ID);
		String userid = this.userId.getText();
		String password = this.passwordText.getText();
		try {
			store.setValue("userid", userid);
			store.save();
			Activator.savePassword(userid, password);
		} catch (IOException e) {
			Activator.log(e);
		}
	}

}
