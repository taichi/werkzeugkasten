package werkzeugkasten.common.viewers;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Image;

public interface ColumnDescriptor<T> {
	String name();

	CellEditor cellEditor();

	String getText(T element);

	Image getImage(T element);

	Object getValue(T element);

	void setValue(T element, Object value);

	boolean canModify();
}