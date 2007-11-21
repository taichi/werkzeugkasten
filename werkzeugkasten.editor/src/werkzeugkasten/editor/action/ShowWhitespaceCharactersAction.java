package werkzeugkasten.editor.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import werkzeugkasten.editor.Activator;

public class ShowWhitespaceCharactersAction implements
		IWorkbenchWindowActionDelegate, IEditorActionDelegate {

	public ShowWhitespaceCharactersAction() {
		togglePainterState(Activator.getCheckedFromPref());
	}

	public void init(IWorkbenchWindow window) {
	}

	public void run(IAction action) {
		togglePainterState(action.isChecked());
		Activator.getDefault().update(action.isChecked());
	}

	protected void togglePainterState(boolean maybeInstall) {
		if (maybeInstall) {
			Activator.getDefault().addToAllEditors();
		} else {
			Activator.getDefault().removeFromAllEditors();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		update(action);
	}

	public void setActiveEditor(IAction action, IEditorPart editor) {
		update(action);
	}

	public void update(IAction action) {
		action
				.setEnabled(Activator.getDefault().getActiveTextViewer() instanceof ITextViewerExtension2);
		boolean stored = Activator.getCheckedFromPref();
		if (stored != action.isChecked()) {
			action.setChecked(stored);
		}
	}

	public void dispose() {
		Activator.getDefault().removeFromAllEditors();
	}

}
