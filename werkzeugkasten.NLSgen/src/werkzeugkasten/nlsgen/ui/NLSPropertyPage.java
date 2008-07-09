/**
 * 
 */
package werkzeugkasten.nlsgen.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import werkzeugkasten.common.runtime.AdaptableUtil;

/**
 * @author taichi
 */
public class NLSPropertyPage extends PropertyPage {

	protected Combo generatorType;

	protected Text description;

	protected Text destPath;

	protected Button addRuntime;

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
		layout.numColumns = 2;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		return composite;
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();

	}

	@Override
	public boolean performOk() {
		return false;
	}

	protected IFile getSelected() {
		return AdaptableUtil.to(getElement(), IFile.class);
	}
}
