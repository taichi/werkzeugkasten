package werkzeugkasten.twowaysql.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.nls.Strings;

public class TwoWaySqlEditor extends TextEditor {

	protected TwoWaySqlConfiguration configuration = new TwoWaySqlConfiguration();

	public TwoWaySqlEditor() {
		configuration.initialize();
		setSourceViewerConfiguration(configuration);
		setDocumentProvider(Activator.getDocumentProvider());
	}

	@Override
	protected void createActions() {
		super.createActions();
		IAction action = new ContentAssistAction(Strings.getBundle(),
				"ContentAssistProposal_", this);
		action
				.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction("ContentAssistProposal", action);
		markAsStateDependentAction("ContentAssistProposal", true);
	}

	@Override
	public void dispose() {
		this.configuration.dispose();
		super.dispose();
	}
}
