package werkzeugkasten.editor.action;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;

import werkzeugkasten.common.ui.WorkbenchUtil;
import werkzeugkasten.editor.Activator;
import werkzeugkasten.editor.Constants;
import werkzeugkasten.editor.text.WhitespaceCharacterPainter;

public class ShowWhitespaceCharactersAction extends Action implements
		IWorkbenchWindowActionDelegate, IEditorActionDelegate {

	protected static Map<IWorkbenchPartReference, WhitespaceCharacterPainter> painters = new ConcurrentHashMap<IWorkbenchPartReference, WhitespaceCharacterPainter>();
	protected static Map<IWorkbenchPartReference, ITextViewer> viewers = new ConcurrentHashMap<IWorkbenchPartReference, ITextViewer>();

	public ShowWhitespaceCharactersAction() {
		setChecked(getCheckedFromPref());
	}

	public void init(IWorkbenchWindow window) {
		setChecked(getCheckedFromPref());
		window.getActivePage().addPartListener(new IPartListener2() {

			public void partOpened(IWorkbenchPartReference partRef) {
				if (isChecked()) {
					IEditorPart editor = null;
					if (partRef instanceof IEditorReference) {
						IEditorReference edref = (IEditorReference) partRef;
						editor = edref.getEditor(true);
					} else {
						IWorkbenchPart part = partRef.getPart(false);
						if (part instanceof IEditorPart) {
							editor = (IEditorPart) part;
						} else {
							editor = (IEditorPart) part
									.getAdapter(IEditorPart.class);
						}
					}
					addPainter(partRef, to(to(editor)));
				}
			}

			public void partClosed(IWorkbenchPartReference partRef) {
				WhitespaceCharacterPainter painter = painters.get(partRef);
				ITextViewer viewer = viewers.get(partRef);
				removePainter(painter, viewer);
				painters.remove(partRef);
				viewers.remove(partRef);
			}

			public void partActivated(IWorkbenchPartReference partRef) {
				// do nothing ...
			}

			public void partBroughtToTop(IWorkbenchPartReference partRef) {
				// do nothing ...
			}

			public void partDeactivated(IWorkbenchPartReference partRef) {
				// do nothing ...
			}

			public void partHidden(IWorkbenchPartReference partRef) {
				// do nothing ...
			}

			public void partInputChanged(IWorkbenchPartReference partRef) {
				// do nothing ...
			}

			public void partVisible(IWorkbenchPartReference partRef) {
				// do nothing ...
			}
		});
	}

	public void run(IAction action) {
		setChecked(action.isChecked());
		togglePainterState(action.isChecked());
		Preferences store = Activator.getDefault().getPluginPreferences();
		if (store != null) {
			store.setValue(Constants.PREF_SHOW_WHITESPACE, action.isChecked());
		}
		Activator.getDefault().savePluginPreferences();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		update(action);
	}

	public void setActiveEditor(IAction action, IEditorPart editor) {
		update(action);
	}

	protected void synchronizeWithPreference(IAction action) {
		boolean checked = getCheckedFromPref();
		if (checked != action.isChecked()) {
			setChecked(checked);
		}
	}

	protected boolean getCheckedFromPref() {
		boolean checked = false;

		Preferences store = Activator.getDefault().getPluginPreferences();
		if (store != null) {
			checked = store.getBoolean(Constants.PREF_SHOW_WHITESPACE);
		}
		return checked;
	}

	protected void togglePainterState(boolean maybeInstall) {
		if (maybeInstall) {
			addToAllEditors();
		} else {
			removeFromAllEditors();
		}
	}

	protected void addToAllEditors() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
		for (IWorkbenchWindow window : windows) {
			IWorkbenchPage[] pages = window.getPages();
			for (IWorkbenchPage page : pages) {
				IEditorReference[] references = page.getEditorReferences();
				for (IEditorReference ref : references) {
					IEditorPart ep = ref.getEditor(false);
					AbstractTextEditor ate = to(to(ep));
					addPainter(ref, ate);
				}
			}
		}
	}

	protected void addPainter(IWorkbenchPartReference ref,
			AbstractTextEditor ate) {
		ITextViewer viewer = getTextViewer(ate);
		if (viewer != null && painters.get(ref) == null) {
			WhitespaceCharacterPainter painter = new WhitespaceCharacterPainter(
					viewer);
			((ITextViewerExtension2) viewer).addPainter(painter);
			painters.put(ref, painter);
			viewers.put(ref, viewer);
		}
	}

	public void update(IAction action) {
		setEnabled(getTextViewer() instanceof ITextViewerExtension2);
		synchronizeWithPreference(action);
	}

	protected static Method getSourceViewer;
	static {
		try {
			getSourceViewer = AbstractTextEditor.class
					.getDeclaredMethod("getSourceViewer");
			getSourceViewer.setAccessible(true);
		} catch (Exception e) {
			Activator.log(e);
		}
	}

	protected ITextViewer getTextViewer() {
		IEditorPart part = WorkbenchUtil.getActiveEditor();
		return getTextViewer(to(to(part)));
	}

	protected ITextViewer getTextViewer(AbstractTextEditor ate) {
		try {
			if (ate != null) {
				Object obj = getSourceViewer.invoke(ate);
				if (obj instanceof ITextViewer) {
					return (ITextViewer) obj;
				}
			}
		} catch (Exception e) {
			Activator.log(e);
		}
		return null;
	}

	protected ITextEditor to(IEditorPart part) {
		if (part instanceof ITextEditor) {
			return (ITextEditor) part;
		} else if (part != null) {
			return (ITextEditor) part.getAdapter(ITextEditor.class);
		}
		return null;
	}

	protected AbstractTextEditor to(ITextEditor te) {
		if (te instanceof AbstractTextEditor) {
			return (AbstractTextEditor) te;
		} else if (te != null) {
			return (AbstractTextEditor) te.getAdapter(AbstractTextEditor.class);
		}
		return null;
	}

	protected void removeFromAllEditors() {
		for (IWorkbenchPartReference ref : painters.keySet()) {
			WhitespaceCharacterPainter painter = painters.get(ref);
			ITextViewer viewer = viewers.get(ref);
			removePainter(painter, viewer);
		}
		painters.clear();
		viewers.clear();
	}

	public void dispose() {
		removeFromAllEditors();
	}

	private void removePainter(WhitespaceCharacterPainter painter,
			ITextViewer viewer) {
		if (painter != null && viewer != null) {
			((ITextViewerExtension2) viewer).removePainter(painter);
		}
	}
}
