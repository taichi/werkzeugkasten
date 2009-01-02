package werkzeugkasten.twowaysql.editor;

import org.eclipse.ui.editors.text.TextEditor;

import werkzeugkasten.twowaysql.Activator;

public class TwoWaySqlEditor extends TextEditor {

	protected TwoWaySqlConfiguration configuration = new TwoWaySqlConfiguration();

	public TwoWaySqlEditor() {
		configuration.initialize();
		setSourceViewerConfiguration(configuration);
		setDocumentProvider(Activator.getDocumentProvider());
	}

	@Override
	public void dispose() {
		this.configuration.dispose();
		super.dispose();
	}
}
