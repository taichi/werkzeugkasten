package twowaysqleditor;

import java.util.ResourceBundle;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;

public class SqlEditor extends TextEditor {

	private static final String ACTION_ID_CONTENTASSIST_PROPOSALS = "CONTENTASSIST_PROPOSALS";
	private static final String ACTION_ID_FORMAT = "FORMAT";
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
				ACTION_ID_CONTENTASSIST_PROPOSALS + ".", this,
				ISourceViewer.CONTENTASSIST_PROPOSALS);
		contentassist
				.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction(ACTION_ID_CONTENTASSIST_PROPOSALS, contentassist);

		TextOperationAction format = new TextOperationAction(BUNDLE,
				ACTION_ID_FORMAT + ".", this, ISourceViewer.FORMAT);
		format.setActionDefinitionId(Constants.DEFID_FORMAT);
		setAction(ACTION_ID_FORMAT, format);
	}

	@Override
	protected void editorContextMenuAboutToShow(IMenuManager menu) {
		super.editorContextMenuAboutToShow(menu);
		menu.add(new Separator());
		addAction(menu, ACTION_ID_FORMAT);
	}

	@Override
	public void dispose() {
		this.colorManager.dispose();
		super.dispose();
	}
}
