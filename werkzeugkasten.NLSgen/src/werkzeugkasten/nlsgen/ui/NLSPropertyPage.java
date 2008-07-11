/**
 * 
 */
package werkzeugkasten.nlsgen.ui;

import static werkzeugkasten.nlsgen.nls.Strings.LABEL_ADD_RUNTIME;
import static werkzeugkasten.nlsgen.nls.Strings.LABEL_BROWSE;
import static werkzeugkasten.nlsgen.nls.Strings.LABEL_DEST_PATH;
import static werkzeugkasten.nlsgen.nls.Strings.LABEL_GENERATOR_TYPE;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.wiget.ResourceTreeSelectionDialog;
import werkzeugkasten.nlsgen.Activator;
import werkzeugkasten.nlsgen.Constants;
import werkzeugkasten.nlsgen.ResourceGenerator;
import werkzeugkasten.nlsgen.ResourceGeneratorDesc;
import werkzeugkasten.nlsgen.nls.Strings;

/**
 * @author taichi
 */
public class NLSPropertyPage extends PropertyPage {

	protected Combo generatorType;

	protected Label description;

	protected Text destPath;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse
	 * .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		Label typelabel = new Label(composite, SWT.NONE);
		typelabel.setText(LABEL_GENERATOR_TYPE);
		this.generatorType = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		this.generatorType
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		String[] types = getGeneratorTypes();
		this.generatorType.setItems(types);
		this.generatorType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = NLSPropertyPage.this.generatorType
						.getSelectionIndex();
				if (index < 1) {
					NLSPropertyPage.this.description.setText("");
					setErrorMessage(null);
				} else {
					NLSPropertyPage.this.description
							.setText(labelToDescText(NLSPropertyPage.this.generatorType
									.getText()));
					verifyRuntime();
				}
			}
		});

		Button addRuntime = new Button(composite, SWT.PUSH);
		addRuntime.setText(LABEL_ADD_RUNTIME);
		addRuntime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = getSelected();
				if (file != null) {
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace()
							.getRoot();
					ResourceTreeSelectionDialog dialog = new ResourceTreeSelectionDialog(
							getShell(), root, IResource.FOLDER
									| IResource.PROJECT);
					dialog.setInitialSelection(file.getParent());
					dialog.setAllowMultiple(false);
					if (dialog.open() == Window.OK) {
						Object[] results = dialog.getResult();
						if (results != null && 0 < results.length) {
							IContainer c = AdaptableUtil.to(results[0],
									IContainer.class);
							addRuntime(c);
							verifyRuntime();
						}
					}
				}
			}
		});

		this.description = new Label(composite, SWT.NONE);
		GridData descGD = new GridData(GridData.FILL_HORIZONTAL);
		descGD.horizontalSpan = 3;
		this.description.setLayoutData(descGD);

		Label destlabel = new Label(composite, SWT.NONE);
		destlabel.setText(LABEL_DEST_PATH);
		this.destPath = new Text(composite, SWT.BORDER);
		GridData destPathGD = new GridData(GridData.FILL_HORIZONTAL);
		this.destPath.setLayoutData(destPathGD);

		Button browse = new Button(composite, SWT.PUSH);
		browse.setText(LABEL_BROWSE);
		browse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IFile file = getSelected();
				if (file != null) {
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace()
							.getRoot();
					ResourceTreeSelectionDialog dialog = new ResourceTreeSelectionDialog(
							getShell(), root, IResource.FOLDER
									| IResource.PROJECT);
					dialog.setInitialSelection(file.getParent());
					dialog.setAllowMultiple(false);
					if (dialog.open() == Window.OK) {
						Object[] results = dialog.getResult();
						if (results != null && 0 < results.length) {
							IResource r = (IResource) results[0];
							IPath path = r.getFullPath();
							String fileName = file.getName();
							int index = fileName.indexOf('_');
							if (0 < index) {
								fileName = fileName.substring(0, index);
							}
							path = path.append(fileName);
							path = path.removeFileExtension().addFileExtension(
									"java");
							NLSPropertyPage.this.destPath.setText(path
									.toString());
						}
					}
				}
			}
		});

		setUpStoredValues();

		return composite;
	}

	protected void setUpStoredValues() {
		IFile file = getSelected();
		if (file != null) {
			try {
				String id = file
						.getPersistentProperty(Constants.GENERATOR_TYPE);
				if (StringUtil.isEmpty(id) == false) {
					outer: for (ResourceGeneratorDesc desc : Activator
							.getGeneratorTypes()) {
						if (desc.getId().equals(id)) {
							String msg = desc.getLabel();
							String[] items = this.generatorType.getItems();
							for (int i = 0; i < items.length; i++) {
								if (msg.equals(items[i])) {
									this.generatorType.select(i);
									this.description.setText(desc
											.getDescription());
									break outer;
								}
							}
						}
					}
				}
				String dest = file
						.getPersistentProperty(Constants.GENERATION_DEST);
				if (StringUtil.isEmpty(dest) == false) {
					this.destPath.setText(dest);
				}
			} catch (CoreException e) {
				Activator.log(e);
			}
		}
	}

	protected String[] getGeneratorTypes() {
		List<String> types = new ArrayList<String>();
		types.add("");
		for (ResourceGeneratorDesc rgd : Activator.getGeneratorTypes()) {
			types.add(rgd.getLabel());
		}
		return types.toArray(new String[types.size()]);
	}

	protected String labelToDescText(String label) {
		ResourceGeneratorDesc desc = labelToDesc(label);
		if (desc != null) {
			return desc.getDescription();
		}
		return null;
	}

	protected String labelToId(String label) {
		ResourceGeneratorDesc desc = labelToDesc(label);
		if (desc != null) {
			return desc.getId();
		}
		return null;
	}

	protected ResourceGeneratorDesc labelToDesc(String label) {
		for (ResourceGeneratorDesc rgd : Activator.getGeneratorTypes()) {
			if (rgd.getLabel().equals(label)) {
				return rgd;
			}
		}
		return null;
	}

	protected void verifyRuntime() {
		IFile file = getSelected();
		if (file == null) {
			return;
		}
		IJavaProject javap = JavaCore.create(file.getProject());
		ResourceGeneratorDesc desc = labelToDesc(this.generatorType.getText());
		if (desc != null) {
			ResourceGenerator rg = desc.getInstance();
			if (rg.verifyRuntime(javap)) {
				setErrorMessage(null);
			} else {
				setErrorMessage(Strings.MSG_NEED_RUNTIME);
			}
		}
	}

	protected void addRuntime(IContainer dest) {
		ResourceGeneratorDesc desc = labelToDesc(this.generatorType.getText());
		if (desc != null) {
			ResourceGenerator rg = desc.getInstance();
			rg.addRuntime(dest);
		}
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		this.generatorType.select(0);
		this.description.setText("");
		this.destPath.setText("");
	}

	@Override
	public boolean performOk() {
		final IFile file = getSelected();
		try {
			if (file != null) {
				final String type = labelToId(this.generatorType.getText());
				if (StringUtil.isEmpty(type) == false) {
					file.setPersistentProperty(Constants.GENERATOR_TYPE, type);
				} else {
					file.setPersistentProperty(Constants.GENERATOR_TYPE, null);
				}
				String dest = this.destPath.getText();
				if (StringUtil.isEmpty(dest) == false) {
					file.setPersistentProperty(Constants.GENERATION_DEST, dest);
				} else {
					file.setPersistentProperty(Constants.GENERATION_DEST, null);
				}
				if (StringUtil.isEmpty(type) == false) {
					new WorkspaceJob(Strings.GENERATE_CLASSES) {
						@Override
						public IStatus runInWorkspace(IProgressMonitor monitor)
								throws CoreException {
							ResourceGenerator rg = Activator
									.createResourceGenerator(type);
							if (rg != null) {
								rg.generateFrom(file, monitor);
							}
							return Status.OK_STATUS;
						}
					}.schedule();
				}
				return true;
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
		return false;
	}

	protected IFile getSelected() {
		return AdaptableUtil.to(getElement(), IFile.class);
	}
}
