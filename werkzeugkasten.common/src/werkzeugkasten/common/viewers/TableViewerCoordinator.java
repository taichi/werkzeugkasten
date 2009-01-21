package werkzeugkasten.common.viewers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Item;

public class TableViewerCoordinator<T> extends LabelProvider implements
		ITableLabelProvider, ICellModifier {

	protected TableViewer viewer;
	protected Class<T> rowType;
	protected Map<String, ColumnDescriptor<T>> descriptors;
	protected List<String> properties;

	public TableViewerCoordinator(TableViewer viewer, Class<T> rowType,
			List<ColumnDescriptor<T>> descs) {
		this.viewer = viewer;
		this.rowType = rowType;
		this.properties = new ArrayList<String>(descs.size());
		this.descriptors = new HashMap<String, ColumnDescriptor<T>>(descs
				.size());
		List<CellEditor> editors = new ArrayList<CellEditor>(descs.size());
		for (ColumnDescriptor<T> cd : descs) {
			this.properties.add(cd.name());
			editors.add(cd.cellEditor());
			this.descriptors.put(cd.name(), cd);
		}
		this.viewer.setLabelProvider(this);
		this.viewer.setColumnProperties(this.properties
				.toArray(new String[this.properties.size()]));
		this.viewer.setCellEditors(editors.toArray(new CellEditor[editors
				.size()]));
		this.viewer.setCellModifier(this);
	}

	protected ColumnDescriptor<T> getDescriptor(int index) {
		if (index < this.properties.size()) {
			return this.descriptors.get(this.properties.get(index));
		}
		return null;
	}

	protected ColumnDescriptor<T> getDescriptor(String property) {
		return this.descriptors.get(property);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		ColumnDescriptor<T> cd = getDescriptor(columnIndex);
		element = unwrap(element);
		return cd != null && this.rowType.isInstance(element) ? cd
				.getImage((T) element) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getColumnText(Object element, int columnIndex) {
		ColumnDescriptor<T> cd = getDescriptor(columnIndex);
		element = unwrap(element);
		return cd != null && this.rowType.isInstance(unwrap(element)) ? cd
				.getText((T) element) : "";
	}

	@Override
	public boolean canModify(Object element, String property) {
		ColumnDescriptor<T> cd = getDescriptor(property);
		return cd != null && this.rowType.isInstance(unwrap(element)) ? cd
				.canModify() : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getValue(Object element, String property) {
		ColumnDescriptor<T> cd = getDescriptor(property);
		element = unwrap(element);
		return cd != null && this.rowType.isInstance(element) ? cd
				.getValue((T) element) : "";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modify(Object item, String property, Object value) {
		ColumnDescriptor<T> cd = getDescriptor(property);
		Object element = unwrap(item);
		if (cd != null && this.rowType.isInstance(element)) {
			cd.setValue((T) element, value);
			this.viewer.update(element, new String[] { property });
		}
	}

	protected Object unwrap(Object o) {
		if (o instanceof Item) {
			Item i = (Item) o;
			return i.getData();
		}
		return o;
	}
}
