package werkzeugkasten.twowaysql.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.nls.Strings;

public class TwoWaySqlEditor extends TextEditor {

	private static final String ACTION_ID_CONTENTASSIST_PROPOSALS = "ContentAssistProposal";

	protected TwoWaySqlConfiguration configuration;

	public TwoWaySqlEditor(IPreferenceStore pref) {
		configuration = new TwoWaySqlConfiguration(pref);
		configuration.initialize();
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

	@Override
	public void dispose() {
		this.configuration.dispose();
		super.dispose();
	}
}
