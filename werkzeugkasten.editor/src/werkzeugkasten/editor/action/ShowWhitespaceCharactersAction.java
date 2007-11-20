package werkzeugkasten.editor.action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import werkzeugkasten.editor.Activator;
import werkzeugkasten.editor.text.WhitespaceCharacterPainter;

public class ShowWhitespaceCharactersAction implements
		IWorkbenchWindowActionDelegate, IEditorActionDelegate {

	protected static Map<IWorkbenchPartReference, WhitespaceCharacterPainter> painters = new ConcurrentHashMap<IWorkbenchPartReference, WhitespaceCharacterPainter>();
	protected static Map<IWorkbenchPartReference, ITextViewer> viewers = new ConcurrentHashMap<IWorkbenchPartReference, ITextViewer>();

	public ShowWhitespaceCharactersAction() {
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
		synchronizeWithPreference(action);
	}

	protected void synchronizeWithPreference(IAction action) {
		boolean stored = Activator.getCheckedFromPref();
		if (stored != action.isChecked()) {
			action.setChecked(stored);
		}
	}

	public void dispose() {
		Activator.getDefault().removeFromAllEditors();
	}

}
