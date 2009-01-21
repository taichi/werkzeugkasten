package werkzeugkasten.twowaysql.editor;

import org.eclipse.core.resources.IFile;
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
import werkzeugkasten.twowaysql.editor.conf.ContextSettings;
import werkzeugkasten.twowaysql.editor.widget.ContextPage;
import werkzeugkasten.twowaysql.nls.Strings;

public class TwoWaySqlEditorContainer extends MultiPageEditorPart {

	protected TwoWaySqlEditor delegate;

	protected ContextPage contextPage;

	protected ContextSettings settings;

	protected ScopedPreferenceStore store;

	@Override
	protected void createPages() {
		this.store = new ScopedPreferenceStore(new ProjectScope(getProject()),
				Constants.ID_PLUGIN);
		this.settings = ContextSettings.read(this.store, getFile());
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
		this.contextPage = new ContextPage(getProject(), settings,
				getEditorSite());
		Composite composite = this.contextPage.layout(getContainer());
		int index = addPage(composite);
		setPageText(index, Strings.ContextPage_label);
	}

	protected IFile getFile() {
		IFileEditorInput input = AdaptableUtil.to(getEditorInput(),
				IFileEditorInput.class);
		if (input != null) {
			return input.getFile();
		}
		return null;
	}

	protected IProject getProject() {
		IFile file = getFile();
		if (file != null) {
			return file.getProject();
		}
		return null;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (this.delegate != null) {
			this.delegate.doSave(monitor);
			ContextSettings.save(this.store, getFile(), this.settings);
			this.contextPage.modified(false);
		}
	}

	@Override
	public void doSaveAs() {
		if (this.delegate != null) {
			this.delegate.doSaveAs();
			ContextSettings.save(this.store, getFile(), this.settings);
			setPageText(0, this.delegate.getTitle());
			setInput(this.delegate.getEditorInput());
			this.contextPage.modified(false);
		}
	}

	@Override
	public boolean isDirty() {
		return this.delegate == null ? false : this.delegate.isDirty()
				|| this.contextPage.modified();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return this.delegate != null ? this.delegate.isSaveAsAllowed() : false;
	}
}
