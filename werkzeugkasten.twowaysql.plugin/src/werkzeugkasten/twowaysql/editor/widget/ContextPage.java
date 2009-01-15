package werkzeugkasten.twowaysql.editor.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.nls.Strings;

public class ContextPage {

	protected IProject project;
	protected IRunnableContext context;

	protected Text className;
	protected Combo methods;
	protected List<String> methodSignatures;

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
		this.className.setText(type.getFullyQualifiedName());
		this.methodSignatures = new ArrayList<String>();
		List<String> methodView = new ArrayList<String>();
		for (IMethod mtd : type.getMethods()) {
			this.methodSignatures.add(mtd.getSignature());
			StringBuilder stb = new StringBuilder();
			String[] types = mtd.getParameterTypes();
			stb.append(mtd.getElementName());
			stb.append("(");
			for (int i = 0, length = types.length; i < length;) {
				stb.append(Signature.toString(types[i]));
				if (++i < length) {
					stb.append(",");
				}
			}
			stb.append(")");
			methodView.add(stb.toString());
		}
		this.methods
				.setItems(methodView.toArray(new String[methodView.size()]));

	}

	public Composite layout(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 4;
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		// クラス名のテキストエリア
		this.className = new Text(composite, SWT.READ_ONLY | SWT.BORDER);
		// メソッド名のコンボボックス
		this.methods = new Combo(composite, SWT.READ_ONLY | SWT.BORDER);

		// クラスを検索するダイアログを出すBrowseボタン
		Button browse = new Button(composite, SWT.PUSH);
		browse.setText(Strings.Browse);
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

		// グリッド(全カラム編集可)
		// - 型名
		// - 変数名
		// TODO - デバッグ用サンプルデータ

		// メソッドからは類推出来ない変数名を登録する為のボタン
		// 変数名を削除するボタン
		return null;
	}

	public String getClassName() {
		return this.className.getText();
	}

	public String getMethodSignature() {
		return methodSignatures.get(methods.getSelectionIndex());
	}
}
