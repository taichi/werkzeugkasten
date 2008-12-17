package werkzeugkasten.twowaysql.editor;

import org.eclipse.ui.editors.text.TextEditor;

import werkzeugkasten.twowaysql.editor.conf.ColorManager;

public class TwoWaySqlEditor extends TextEditor {

	public TwoWaySqlEditor() {

		setSourceViewerConfiguration(new TwoWaySqlConfiguration(ColorManager
				.getDefault()));
		setDocumentProvider(new TwoWaySqlDocumentProvider());
	}

	@Override
	public void dispose() {
		ColorManager.getDefault().dispose();
		super.dispose();
	}
}
