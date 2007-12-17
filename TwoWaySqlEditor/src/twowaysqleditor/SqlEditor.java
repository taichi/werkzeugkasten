package twowaysqleditor;

import org.eclipse.ui.editors.text.TextEditor;

public class SqlEditor extends TextEditor {

	protected ColorManager colorManager;

	public SqlEditor() {
		this.colorManager = new ColorManager();
		setSourceViewerConfiguration(new SqlConfiguration(this.colorManager));
		setDocumentProvider(new SqlDocumentProvider());
	}

	@Override
	public void dispose() {
		this.colorManager.dispose();
		super.dispose();
	}
}
