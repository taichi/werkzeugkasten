package moeclipse;

import java.io.File;
import java.net.URL;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jdt.ui.text.folding.IJavaFoldingPreferenceBlock;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import werkzeugkasten.common.util.UrlUtil;

public class JavaFoldingPreferenceBlock implements IJavaFoldingPreferenceBlock {

	protected Button moeMode;
	protected Text picturePath;

	@Override
	public Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		// XXX
		composite.setLayout(layout);
		return composite;
	}

	@Override
	public void initialize() {
		IPreferenceStore myStore = Activator.getDefault().getPreferenceStore();
		this.moeMode.setSelection(myStore.getBoolean(Constants.PREF_MOEMODE));
		this.picturePath
				.setText(myStore.getString(Constants.PREF_PICTURE_PATH));
	}

	@Override
	public void dispose() {
	}

	@Override
	public void performDefaults() {
		this.moeMode.setSelection(false);
		this.picturePath.setText("");
	}

	@Override
	public void performOk() {
		IPreferenceStore preferenceStore = JavaPlugin.getDefault()
				.getPreferenceStore();
		if (this.moeMode.getSelection()) {
			preferenceStore.setValue(
					PreferenceConstants.EDITOR_FOLDING_PROVIDER,
					"moeclipse.provider");
		} else {
			preferenceStore
					.setToDefault(PreferenceConstants.EDITOR_FOLDING_PROVIDER);
		}

		IPreferenceStore myStore = Activator.getDefault().getPreferenceStore();
		myStore.setValue(Constants.PREF_MOEMODE, this.moeMode.getSelection());
		myStore.setValue(Constants.PREF_PICTURE_PATH, toURL());
	}

	protected String toURL() {
		File f = new File(this.picturePath.getText());
		URL url = UrlUtil.toURL(f);
		return url.toExternalForm();
	}

}
