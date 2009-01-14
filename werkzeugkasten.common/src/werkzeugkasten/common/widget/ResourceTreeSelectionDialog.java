package werkzeugkasten.common.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.IWorkbenchAdapter;

import werkzeugkasten.common.runtime.LogUtil;

/**
 * @author taichi
 * 
 */
public class ResourceTreeSelectionDialog extends ElementTreeSelectionDialog {

	/**
	 * @param parent
	 * @param labelProvider
	 * @param contentProvider
	 */
	public ResourceTreeSelectionDialog(Shell parent, IContainer root, int flags) {
		super(parent, new ResourceLabelProvider(),
				new ResourceTreeContentProvider(flags));
		setInput(root);
	}

	private static class ResourceLabelProvider extends LabelProvider implements
			ILabelProvider {
		@Override
		public Image getImage(Object element) {
			Image result = null;
			if (element instanceof IResource) {
				IResource r = (IResource) element;
				IWorkbenchAdapter adapter = (IWorkbenchAdapter) r
						.getAdapter(IWorkbenchAdapter.class);
				if (adapter != null) {
					ImageDescriptor desc = adapter.getImageDescriptor(r);
					if (desc != null) {
						result = desc.createImage();
					}
				}
			}
			return result;
		}

		@Override
		public String getText(Object element) {
			if (element instanceof IResource) {
				IResource r = (IResource) element;
				return r.getName();
			}
			return super.getText(element);
		}
	}

	private static class ResourceTreeContentProvider implements
			ITreeContentProvider {
		private int flags = IResource.NONE;

		public ResourceTreeContentProvider(int flags) {
			this.flags = flags;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof IContainer) {
				IContainer c = (IContainer) parentElement;
				return members(c);
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
		 */
		public Object getParent(Object element) {
			if (element instanceof IResource) {
				IResource r = (IResource) element;
				return r.getParent();
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object element) {
			if (element instanceof IResource) {
				IResource r = (IResource) element;
				return r.getType() == IResource.PROJECT
						|| r.getType() == IResource.FOLDER;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof IContainer) {
				IContainer c = (IContainer) inputElement;
				return members(c);
			}
			return null;
		}

		private Object[] members(IContainer c) {
			final List<IResource> result = new ArrayList<IResource>();
			try {
				IResource[] members = c.members();
				for (int i = 0; i < members.length; i++) {
					IResource resource = members[i];
					if ((resource.getType() & flags) != 0) {
						result.add(resource);
					}
				}
			} catch (CoreException e) {
				LogUtil.log(ResourcesPlugin.getPlugin(), e);
			}
			return result.toArray(new IResource[result.size()]);
		}
	}
}
