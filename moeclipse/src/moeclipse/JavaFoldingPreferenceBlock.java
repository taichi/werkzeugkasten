package moeclipse;

import java.io.File;
import java.net.URL;

import org.eclipse.jdt.internal.ui.text.folding.DefaultJavaFoldingPreferenceBlock;
import org.eclipse.jdt.ui.text.folding.IJavaFoldingPreferenceBlock;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import werkzeugkasten.common.util.UrlUtil;

public class JavaFoldingPreferenceBlock extends
		DefaultJavaFoldingPreferenceBlock implements
		IJavaFoldingPreferenceBlock {

	protected Text picturePath;

	@Override
	public Control createControl(Composite parent) {
		Composite composite = (Composite) super.createControl(parent);

		// TODO ラベル
		this.picturePath = new Text(composite, SWT.NONE);
		// TODO 参照ボタン

		return composite;
	}

	@Override
	public void initialize() {
		super.initialize();
		IPreferenceStore myStore = Activator.getDefault().getPreferenceStore();
		this.picturePath
				.setText(myStore.getString(Constants.PREF_PICTURE_PATH));
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
