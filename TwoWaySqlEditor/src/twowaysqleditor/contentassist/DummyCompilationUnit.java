package twowaysqleditor.contentassist;

import java.util.WeakHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.Signature;

import twowaysqleditor.Activator;
import twowaysqleditor.util.AdaptableUtil;

public class DummyCompilationUnit {

	public static final String PREFIX_DUMMY_TYPE = "TwoWaySqlXxx_";
	protected static WeakHashMap<IMethod, DummyCompilationUnit> cache = new WeakHashMap<IMethod, DummyCompilationUnit>();

	protected ICompilationUnit unit;

	protected int before = 0;

	protected DummyCompilationUnit(ICompilationUnit unit) {
		this.unit = unit;
	}

	public static synchronized DummyCompilationUnit get(IMethod method) {
		if (method != null) {
			DummyCompilationUnit unit = cache.get(method);
			if (unit == null) {
				if (method.exists()) {
					unit = create(method);
					cache.put(method, unit);
					return unit;
				}
			} else {
				if (method.exists()) {
					return unit;
				} else {
					cache.remove(method);
				}
			}
		}
		return null;
	}

	public static DummyCompilationUnit create(IMethod method) {
		try {
			IJavaElement element = method.getDeclaringType()
					.getCompilationUnit().getParent();
			IPackageFragment fragment = AdaptableUtil.to(element,
					IPackageFragment.class);
			if (fragment != null) {
				ICompilationUnit unit = fragment
						.getCompilationUnit(toDummyType(method) + ".java");
				unit = unit.getWorkingCopy(null);
				DummyCompilationUnit dummy = new DummyCompilationUnit(unit);
				dummy.implement(method);
				return dummy;
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
		return null;
	}

	public static String toDummyType(IMethod method) {
		return PREFIX_DUMMY_TYPE + method.getElementName();
	}

	public int getBefore() {
		return this.before;
	}

	public ICompilationUnit getUnit() {
		return this.unit;
	}

	protected void implement(IMethod method) throws CoreException {
		StringBuilder bef = new StringBuilder();
		bef.append("public class ");
		bef.append(toDummyType(method));
		bef.append("{\r\n");
		bef.append(Flags.toString(method.getFlags()));
		bef.append(" ");
		bef.append(Signature.toString(method.getReturnType()));
		bef.append(" ");
		bef.append(method.getElementName());
		bef.append("(");
		String[] types = method.getParameterTypes();
		String[] names = method.getParameterNames();
		if (types != null && names != null && 0 < types.length
				&& 0 < names.length && types.length == names.length) {
			for (int i = 0; i < types.length; i++) {
				bef.append(Signature.toString(types[i]));
				bef.append(" ");
				bef.append(names[i]);
				bef.append(",");
			}
			bef.setLength(bef.length() - 1);
		}
		bef.append(") throws Exception{\r\n");
		this.before = bef.length();

		bef.append("}}");

		IBuffer buffer = unit.getBuffer();
		if (buffer != null) {
			buffer.setContents(bef.toString());
		}
	}

}
