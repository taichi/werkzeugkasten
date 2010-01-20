package moeclipse;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jdt.ui.text.folding.IJavaFoldingPreferenceBlock;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class JavaFoldingPreferenceBlock implements IJavaFoldingPreferenceBlock {

	protected Button moeMode;
	protected Text picturePath;

	@Override
	public Control createControl(Composite parent) {
		return null;
	}

	@Override
	public void initialize() {
		// TODO load from strage.
	}

	@Override
	public void dispose() {
		this.moeMode.dispose();
		this.picturePath.dispose();
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
					"nekolipse.provider");
		} else {
			preferenceStore
					.setToDefault(PreferenceConstants.EDITOR_FOLDING_PROVIDER);
		}

		IPreferenceStore myStore = Activator.getDefault().getPreferenceStore();
		myStore.setValue(Constants.PREF_MOEMODE, this.moeMode.getSelection());
		myStore.setValue(Constants.PREF_PICTURE_PATH, this.picturePath
				.getText());
	}

}
