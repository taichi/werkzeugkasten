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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
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
import werkzeugkasten.common.wiget.ResourceTreeSelectionDialog;
import werkzeugkasten.nlsgen.Activator;
import werkzeugkasten.nlsgen.ResourceGeneratorDesc;

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
		String[] types = getGeneratorTypes();
		this.generatorType.setItems(types);
		this.generatorType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = NLSPropertyPage.this.generatorType
						.getSelectionIndex();
				if (0 < index) {
					NLSPropertyPage.this.description
							.setText(labelToDescText(NLSPropertyPage.this.generatorType
									.getText()));
				} else {
					NLSPropertyPage.this.description.setText("");
				}
				// XXX verify runtime.
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
							IResource r = (IResource) results[0];
							// XXX
							// copy jar
							// add classpath entry and attach source.
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
							NLSPropertyPage.this.destPath.setText(r
									.getFullPath().toString());
						}
					}
				}
			}
		});

		return composite;
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

	@Override
	protected void performDefaults() {
		super.performDefaults();
		this.generatorType.select(0);
		this.description.setText("");
		this.destPath.setText("");
	}

	@Override
	public boolean performOk() {
		// XXX
		return false;
	}

	protected IFile getSelected() {
		return AdaptableUtil.to(getElement(), IFile.class);
	}
}
