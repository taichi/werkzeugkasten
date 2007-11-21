package werkzeugkasten.editor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.editor.text.WhitespaceCharacterPainter;

public class StateHolder {
	protected Boolean isChecked = false;
	protected Map<IWorkbenchPartReference, WhitespaceCharacterPainter> painters = new ConcurrentHashMap<IWorkbenchPartReference, WhitespaceCharacterPainter>();
	protected Map<IWorkbenchPartReference, ITextViewer> viewers = new ConcurrentHashMap<IWorkbenchPartReference, ITextViewer>();
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

	public void setChecked(boolean is) {
		this.isChecked = is;
	}

	public boolean isChecked() {
		return this.isChecked;
	}

	public ITextViewer getTextViewer(AbstractTextEditor ate) {
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
		return AdaptableUtil.to(part, ITextEditor.class);
	}

	protected AbstractTextEditor to(ITextEditor te) {
		return AdaptableUtil.to(te, AbstractTextEditor.class);
	}

	public void addPainter(IWorkbenchPartReference partRef) {
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
					editor = (IEditorPart) part.getAdapter(IEditorPart.class);
				}
			}
			addPainter(partRef, to(to(editor)));
		}

	}

	protected void addToAllEditors() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
		for (IWorkbenchWindow window : windows) {
			addPainter(window);
		}
	}

	protected void addPainter(IWorkbenchWindow window) {
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

	public void removePainter(IWorkbenchPartReference partRef) {
		WhitespaceCharacterPainter painter = painters.get(partRef);
		ITextViewer viewer = viewers.get(partRef);
		removePainter(painter, viewer);
		painters.remove(partRef);
		viewers.remove(partRef);
	}

	public void removeFromAllEditors() {
		for (IWorkbenchPartReference ref : painters.keySet()) {
			WhitespaceCharacterPainter painter = painters.get(ref);
			ITextViewer viewer = viewers.get(ref);
			removePainter(painter, viewer);
		}
		painters.clear();
		viewers.clear();
	}

	private void removePainter(WhitespaceCharacterPainter painter,
			ITextViewer viewer) {
		if (painter != null && viewer != null) {
			((ITextViewerExtension2) viewer).removePainter(painter);
		}
	}
}
