package werkzeugkasten.twowaysql.editor;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.editor.widget.ContextPage;
import werkzeugkasten.twowaysql.nls.Strings;

public class TwoWaySqlEditorContainer extends MultiPageEditorPart {

	protected TwoWaySqlEditor delegate;

	protected ContextPage contextPage;

	protected ScopedPreferenceStore store;

	@Override
	protected void createPages() {
		this.store = new ScopedPreferenceStore(new ProjectScope(getProject()),
				Constants.ID_PLUGIN);
		setUpEditorPage();
		setUpContextPage();
	}

	protected void setUpEditorPage() {
		try {
			this.delegate = new TwoWaySqlEditor(this.store);
			int index = addPage(this.delegate, getEditorInput());
			setPageText(index, this.delegate.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(), e.getMessage(), e
					.getMessage(), e.getStatus());
			Activator.log(e);
		}
	}

	protected void setUpContextPage() {
		this.contextPage = new ContextPage(getProject(), getSite()
				.getWorkbenchWindow());
		Composite composite = this.contextPage.layout(getContainer());
		int index = addPage(composite);
		setPageText(index, Strings.ContextPage_label);
	}

	protected IProject getProject() {
		IFileEditorInput input = AdaptableUtil.to(getEditorInput(),
				IFileEditorInput.class);
		if (input != null) {
			return input.getFile().getProject();
		}
		return null;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (this.delegate != null) {
			this.delegate.doSave(monitor);
		}
	}

	@Override
	public void doSaveAs() {
		if (this.delegate != null) {
			this.delegate.doSaveAs();
			setPageText(0, this.delegate.getTitle());
			setInput(this.delegate.getEditorInput());
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return this.delegate != null ? this.delegate.isSaveAsAllowed() : false;
	}
}
