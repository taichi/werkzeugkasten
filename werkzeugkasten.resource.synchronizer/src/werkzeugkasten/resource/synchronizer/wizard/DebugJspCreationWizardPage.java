package werkzeugkasten.resource.synchronizer.wizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.navigator.ResourceComparator;

import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.widget.ResourceTreeSelectionDialog;
import werkzeugkasten.resource.synchronizer.Activator;
import werkzeugkasten.resource.synchronizer.nls.Strings;

/**
 * @author taichi
 * 
 */
public class DebugJspCreationWizardPage extends WizardPage {

	public static final String NAME = DebugJspCreationWizardPage.class
			.getName();
	protected IProject project;

	protected Text contextRoot;

	protected Text output;

	protected DebugJspCreationWizardPage(IProject project) {
		this(NAME, project);
	}

	protected DebugJspCreationWizardPage(String pageName, IProject project) {
		super(pageName);
		this.project = project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL));

		contextRoot = createInputArea(composite,
				Strings.LABEL_CONTEXT_ROOT_DIR,
				Strings.MSG_SELECT_CONTEXT_ROOT_DIR);
		contextRoot.setEditable(false);

		output = createInputArea(composite, Strings.LABEL_OUT_PUT_DIR,
				Strings.MSG_SELECT_OUTPUT_DIR);

		output.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				try {
					setErrorMessage(null);
					setPageComplete(false);
					String s = ((Text) e.widget).getText();
					if (s == null || s.length() < 1) {
						setErrorMessage(Strings.ERR_SELECT_OUTPUT_DIR);
					} else {
						IPath root = new Path(contextRoot.getText());
						IPath out = new Path(s);
						if (root.isPrefixOf(out)) {
							IContainer c = ProjectUtil.getWorkspaceRoot()
									.getFolder(out);
							if (c.exists() == false || c.members().length < 1) {
								setPageComplete(true);
							} else {
								setErrorMessage(Strings.ERR_OUTPUT_DIR_ALREADY_EXISTS);
							}
						} else {
							setErrorMessage(Strings.ERR_OUTPUT_DIR_HAS_ROOT);
						}
					}
				} catch (CoreException ex) {
					Activator.log(ex);
				}
			}
		});

		contextRoot.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String s = e.text;
				String o = output.getText();
				if ((o == null || o.length() < 1) && s != null
						&& 0 < s.length()) {
					IPath p = new Path(s).append("error");
					output.setText(p.toString());
				}
			}
		});

		setErrorMessage(null);
		setMessage(null);
		setPageComplete(false);
		setControl(composite);
	}

	private Text createInputArea(Composite composite, String label,
			final String msg) {
		new Label(composite, SWT.LEFT).setText(label);
		final Text t = new Text(composite, SWT.BORDER);
		t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button b = new Button(composite, SWT.PUSH);
		b.setText(Strings.LABEL_BROWSE);
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				ResourceTreeSelectionDialog dialog = new ResourceTreeSelectionDialog(
						getContainer().getShell(), project, IResource.FOLDER);
				dialog.setComparator(new ResourceComparator(
						ResourceComparator.NAME));
				dialog.setMessage(msg);
				if (dialog.open() == Window.OK) {
					Object[] result = dialog.getResult();
					if (result != null && 0 < result.length
							&& result[0] instanceof IContainer) {
						IContainer c = (IContainer) result[0];
						t.setText(c.getFullPath().toString());
					}
				}
			}
		});
		return t;
	}

	protected String getContextRoot() {
		return this.contextRoot.getText();
	}

	protected String getOutputDir() {
		return this.output.getText();
	}
}
