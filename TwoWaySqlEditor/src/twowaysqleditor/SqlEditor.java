package twowaysqleditor;

import java.util.ResourceBundle;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;

import twowaysqleditor.util.AdaptableUtil;
import twowaysqleditor.util.ColorManager;

public class SqlEditor extends TextEditor {

	private static final String ACTION_ID_CONTENTASSIST_PROPOSALS = "CONTENTASSIST_PROPOSALS";
	private static final String ACTION_ID_FORMAT = "FORMAT";
	protected static final ResourceBundle BUNDLE;

	static {
		BUNDLE = ResourceBundle.getBundle(SqlEditor.class.getName());
	}

	protected ColorManager colorManager;
	protected EditorContext context;

	public SqlEditor() {
		this.colorManager = new ColorManager();
		this.context = new EditorContext();
		setSourceViewerConfiguration(new SqlConfiguration(this.colorManager,
				context));
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
	protected void doSetInput(IEditorInput input) throws CoreException {
		IFile file = AdaptableUtil.to(input, IFile.class);
		this.context.setSqlFile(file);
		super.doSetInput(input);
	}

	@Override
	public void dispose() {
		this.colorManager.dispose();
		super.dispose();
	}
}
