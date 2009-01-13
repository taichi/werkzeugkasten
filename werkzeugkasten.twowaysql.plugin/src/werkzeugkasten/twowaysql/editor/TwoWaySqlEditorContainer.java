package werkzeugkasten.twowaysql.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.nls.Strings;

public class TwoWaySqlEditorContainer extends MultiPageEditorPart {

	protected TwoWaySqlEditor delegate;

	@Override
	protected void createPages() {
		setUpEditorPage();
		setUpContextPage();
	}

	protected void setUpEditorPage() {
		try {
			this.delegate = new TwoWaySqlEditor();
			int index = addPage(this.delegate, getEditorInput());
			setPageText(index, this.delegate.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(), e.getMessage(), e
					.getMessage(), e.getStatus());
			Activator.log(e);
		}
	}

	protected void setUpContextPage() {
		Composite composite = createContextPage();
		int index = addPage(composite);
		setPageText(index, Strings.ContextPage_label);
	}

	protected Composite createContextPage() {
		// TODO implement context page
		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;

		// クラス名のテキストエリア
		// メソッド名のコンボボックス
		// クラスを検索するダイアログを出すBrowseボタン

		// グリッド
		// - 型名
		// - 変数名
		// TODO - デバッグ用サンプルデータ

		// メソッドからは類推出来ない変数名を登録する為のボタン

		return composite;
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
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return this.delegate != null ? this.delegate.isSaveAsAllowed() : false;
	}

	@Override
	public void dispose() {
		if (this.delegate != null) {
			this.delegate.dispose();
		}
		super.dispose();
	}
}
