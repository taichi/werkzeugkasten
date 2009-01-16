package werkzeugkasten.twowaysql.editor.widget;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.dialogs.SelectionDialog;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.nls.Strings;

public class ContextPage {

	protected IProject project;
	protected IRunnableContext context;

	public ContextPage(IProject project, IRunnableContext context) {
		this.project = project;
		this.context = context;
	}

	protected IProject getProject() {
		return this.project;
	}

	protected IRunnableContext getContext() {
		return this.context;
	}

	protected void setSelectedDatas(IType type) throws CoreException {
		// this.className.setText(type.getFullyQualifiedName());
		// this.methodSignatures = new ArrayList<String>();
		// List<String> methodView = new ArrayList<String>();
		// for (IMethod mtd : type.getMethods()) {
		// this.methodSignatures.add(mtd.getSignature());
		// StringBuilder stb = new StringBuilder();
		// String[] types = mtd.getParameterTypes();
		// stb.append(mtd.getElementName());
		// stb.append("(");
		// for (int i = 0, length = types.length; i < length;) {
		// stb.append(Signature.toString(types[i]));
		// if (++i < length) {
		// stb.append(",");
		// }
		// }
		// stb.append(")");
		// methodView.add(stb.toString());
		// }
		// this.methods
		// .setItems(methodView.toArray(new String[methodView.size()]));

	}

	public Composite layout(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		Table t = new Table(composite, SWT.BORDER);
		t.setHeaderVisible(true);
		t.setLinesVisible(true);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL);
		data.verticalSpan = 5;
		t.setLayoutData(data);
		// グリッド(全カラム編集可)
		// - 型名
		// - 変数名
		// - デバッグ用サンプルデータ
		TableColumn type = new TableColumn(t, SWT.NONE);
		type.setText("Type");
		type.setWidth(300);
		TableColumn var = new TableColumn(t, SWT.NONE);
		var.setText("Variable");
		var.setWidth(100);
		TableColumn ex = new TableColumn(t, SWT.NONE);
		ex.setText("Example");
		ex.setWidth(200);

		// メソッドからは類推出来ない変数名を登録する為のボタン
		Button add = new Button(composite, SWT.PUSH);
		add.setText("Add");
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		add.setLayoutData(data);

		// 変数名を削除するボタン
		Button remove = new Button(composite, SWT.PUSH);
		remove.setText("Remove");
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		remove.setLayoutData(data);

		// 変数名を全部削除するボタン
		Button clear = new Button(composite, SWT.PUSH);
		clear.setText("Clear");
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		clear.setLayoutData(data);

		// クラスを検索するダイアログを出すBrowseボタン
		Button browse = new Button(composite, SWT.PUSH);
		browse.setText(Strings.Browse);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		browse.setLayoutData(data);
		browse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					IProject project = getProject();
					if (project != null) {
						SelectionDialog dialog = JavaUI
								.createTypeDialog(
										parent.getShell(),
										getContext(),
										project,
										IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES,
										false);
						if (dialog.open() == IDialogConstants.OK_ID) {
							Object[] result = dialog.getResult();
							if (result != null && 0 < result.length) {
								IType type = AdaptableUtil.to(result[0],
										IType.class);
								if (type != null) {
									setSelectedDatas(type);
								}
							}
						}
					}
				} catch (CoreException ex) {
					Activator.log(ex);
				}
			}
		});

		// 選択されているクラス名とメソッド名で、変数をリフレッシュするボタン。(基本は自動動作するが…)
		Button refresh = new Button(composite, SWT.PUSH);
		refresh.setText(Strings.Refresh);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		refresh.setLayoutData(data);

		return composite;
	}
}
