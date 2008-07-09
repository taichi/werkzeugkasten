package werkzeugkasten.common.jdt;

import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;

/**
 * @author taichi
 * 
 */
public class JavaElementDeltaAcceptor {

	public static void accept(IJavaElementDelta delta, Visitor visitor) {
		if (visitor.preVisit(delta) && visitor.visit(delta.getElement())
				&& visitor.postVisit(delta)) {
			accept(delta.getAffectedChildren(), visitor);
		}
	}

	public static void accept(IJavaElementDelta[] deltas, Visitor visitor) {
		for (int i = 0; i < deltas.length; i++) {
			accept(deltas[i], visitor);
		}
	}

	public static abstract class Visitor {

		protected boolean preVisit(IJavaElementDelta delta) {
			return true;
		}

		protected boolean postVisit(IJavaElementDelta delta) {
			return true;
		}

		protected boolean visit(IJavaElement element) {
			switch (element.getElementType()) {
			case IJavaElement.JAVA_MODEL: {
				return visit((IJavaModel) element);
			}
			case IJavaElement.JAVA_PROJECT: {
				return visit((IJavaProject) element);
			}
			case IJavaElement.PACKAGE_FRAGMENT: {
				return visit((IPackageFragment) element);
			}
			case IJavaElement.COMPILATION_UNIT: {
				return visit((ICompilationUnit) element);
			}
			case IJavaElement.CLASS_FILE: {
				return visit((IClassFile) element);
			}
			default:
				break;
			}
			return true;
		}

		protected boolean visit(IJavaModel model) {
			return true;
		}

		protected boolean visit(IJavaProject project) {
			return true;
		}

		protected boolean visit(IPackageFragmentRoot root) {
			return true;
		}

		protected boolean visit(IPackageFragment fragment) {
			return true;
		}

		protected boolean visit(ICompilationUnit unit) {
			return true;
		}

		protected boolean visit(IClassFile clazz) {
			return true;
		}
	}
}
