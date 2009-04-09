package werkzeugkasten.common.jdt;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.operation.IRunnableWithProgress;

import werkzeugkasten.common.ui.ProgressMonitorUtil;

public class TypeHierarchyWalker implements IRunnableWithProgress {

	protected IType type;
	protected TypeHierarchyHandler handler;

	public TypeHierarchyWalker(IType type, TypeHierarchyHandler handler) {
		this.type = type;
		this.handler = handler;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor = ProgressMonitorUtil.care(monitor);
		this.handler.begin();
		try {
			ITypeHierarchy hierarchy = type.newSupertypeHierarchy(monitor);
			IType[] superTypes = hierarchy.getAllSuperclasses(type);
			for (IType t : superTypes) {
				if (this.handler.handle(t) == false) {
					break;
				}
			}
		} catch (JavaModelException e) {
			throw new InvocationTargetException(e);
		} finally {
			this.handler.done();
		}
	}

	public interface TypeHierarchyHandler {
		void begin();

		boolean handle(IType type) throws JavaModelException;

		void done();
	}

	public abstract class TypeHierarchyMethodHandler implements
			TypeHierarchyHandler {

		@Override
		public boolean handle(IType type) throws JavaModelException {
			for (IMethod m : type.getMethods()) {
				if (handle(m) == false) {
					return false;
				}
			}
			return true;
		}

		public abstract boolean handle(IMethod method)
				throws JavaModelException;
	}
}
