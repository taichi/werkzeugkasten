package moeclipse;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jdt.internal.ui.text.folding.DefaultJavaFoldingPreferenceBlock;
import org.eclipse.jdt.ui.text.folding.IJavaFoldingPreferenceBlock;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.UrlUtil;

public class JavaFoldingPreferenceBlock extends
		DefaultJavaFoldingPreferenceBlock implements
		IJavaFoldingPreferenceBlock {

	protected Text picturePath;

	@Override
	public Control createControl(final Composite parent) {
		Composite c = (Composite) super.createControl(parent);

		Composite composite = new Composite(c, SWT.NONE);

		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.verticalSpacing = 0;
		layout.horizontalSpacing = 5;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.FILL;
		composite.setLayoutData(data);

		Label label = new Label(composite, SWT.NONE);
		label.setText(Strings.LABEL_PICTUREPATH);
		data = new GridData(SWT.BEGINNING);
		label.setLayoutData(data);

		this.picturePath = new Text(composite, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.FILL;
		data.widthHint = 250;
		this.picturePath.setLayoutData(data);

		Button browse = new Button(composite, SWT.PUSH);
		browse.setText(Strings.LABEL_BROWSE);
		browse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(parent.getShell());
				String s = dialog.open();
				picturePath.setText(s);
			}
		});
		return c;
	}

	@Override
	public void initialize() {
		super.initialize();
		IPreferenceStore myStore = Activator.getDefault().getPreferenceStore();
		String s = myStore.getString(Constants.PREF_PICTURE_PATH);
		try {
			if (StringUtil.isEmpty(s) == false) {
				URL url = new URL(s);
				this.picturePath.setText(new File(url.getPath())
						.getAbsolutePath());
			}
		} catch (MalformedURLException e) {
			Activator.log(e);
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void performDefaults() {
		super.performDefaults();
		this.picturePath.setText("");
	}

	@Override
	public void performOk() {
		super.performOk();
		IPreferenceStore myStore = Activator.getDefault().getPreferenceStore();
		myStore.setValue(Constants.PREF_PICTURE_PATH, toURL());
	}

	protected String toURL() {
		File f = new File(this.picturePath.getText());
		URL url = UrlUtil.toURL(f);
		return url.toExternalForm();
	}

}
