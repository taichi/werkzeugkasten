package werkzeugkasten.twowaysql.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.editor.conf.ContextSettings;
import werkzeugkasten.twowaysql.nls.Strings;

public class TwoWaySqlEditor extends TextEditor {

	private static final String ACTION_ID_CONTENTASSIST_PROPOSALS = "ContentAssistProposal";

	protected TwoWaySqlConfiguration configuration;

	public TwoWaySqlEditor(IPreferenceStore pref, ContextSettings settings) {
		this.configuration = new TwoWaySqlConfiguration(this, pref, settings);
		this.configuration.initialize();
		setSourceViewerConfiguration(configuration);
		setDocumentProvider(Activator.getDocumentProvider());
	}

	@Override
	protected void createActions() {
		super.createActions();
		IAction action = new ContentAssistAction(Strings.getBundle(),
				ACTION_ID_CONTENTASSIST_PROPOSALS + "_", this);
		action
				.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction(ACTION_ID_CONTENTASSIST_PROPOSALS, action);
		markAsStateDependentAction("ContentAssistProposal", true);
	}

	protected IJavaProject getJavaProject() {
		IEditorInput input = this.getEditorInput();
		if (input instanceof IFileEditorInput) {
			IFileEditorInput fei = (IFileEditorInput) input;
			IFile file = fei.getFile();
			return JavaCore.create(file.getProject());
		}
		return null;
	}

	@Override
	public void dispose() {
		this.configuration.dispose();
		super.dispose();
	}

	@Override
	public Object getAdapter(@SuppressWarnings("unchecked") Class adapter) {
		if (IJavaProject.class.isAssignableFrom(adapter)) {
			return getJavaProject();
		}

		return super.getAdapter(adapter);
	}
}
