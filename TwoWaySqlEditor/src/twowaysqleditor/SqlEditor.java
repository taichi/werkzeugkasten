package twowaysqleditor;

import java.util.ResourceBundle;

import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;

public class SqlEditor extends TextEditor {

	protected static final ResourceBundle BUNDLE;

	static {
		BUNDLE = ResourceBundle.getBundle(SqlEditor.class.getName());
	}

	protected ColorManager colorManager;

	public SqlEditor() {
		this.colorManager = new ColorManager();
		setSourceViewerConfiguration(new SqlConfiguration(this.colorManager));
		setDocumentProvider(new SqlDocumentProvider());
	}

	@Override
	protected void createActions() {
		super.createActions();
		TextOperationAction contentassist = new TextOperationAction(BUNDLE,
				"CONTENT_ASSIST_PROPOSALS.", this,
				ISourceViewer.CONTENTASSIST_PROPOSALS);
		contentassist
				.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction("CONTENTASSIST_PROPOSALS", contentassist);

		TextOperationAction format = new TextOperationAction(BUNDLE, "FORMAT.",
				this, ISourceViewer.FORMAT);
		setAction("FORMAT", format);
	}

	@Override
	public void dispose() {
		this.colorManager.dispose();
		super.dispose();
	}
}
