package werkzeugkasten.twowaysql.editor.widget;

import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.viewers.ColumnDescriptor;
import werkzeugkasten.twowaysql.editor.conf.Variable;
import werkzeugkasten.twowaysql.nls.Strings;

public class TypeColumn implements ColumnDescriptor<Variable> {

	private CellEditor editor;

	public TypeColumn(Table table) {
		TableColumn c = new TableColumn(table, SWT.LEFT);
		c.setText(Strings.Column_Type);
		c.setWidth(300);
		this.editor = new TextCellEditor(table);
	}

	@Override
	public String name() {
		return getClass().getName();
	}

	@Override
	public Image getImage(Variable element) {
		return JavaUI.getSharedImages().getImage(ISharedImages.IMG_OBJS_CLASS);
	}

	@Override
	public String getText(Variable element) {
		return element.type();
	}

	@Override
	public CellEditor cellEditor() {
		return this.editor;
	}

	@Override
	public boolean canModify() {
		return false;
	}

	@Override
	public Object getValue(Variable element) {
		return element.type();
	}

	@Override
	public void setValue(Variable element, Object value) {
		String v = AdaptableUtil.to(value, String.class);
		element.type(v);
	}
}
