package twowaysqleditor.contentassist;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.ui.text.java.CompletionProposalCollector;

import twowaysqleditor.Activator;
import twowaysqleditor.util.AdaptableUtil;

public class DummyCompilationUnit {

	public static final String PREFIX_DUMMY_TYPE = "TwoWaySqlXxx_";

	protected ICompilationUnit unit;

	protected int before = 0;

	protected String dummyClassName;

	protected DummyCompilationUnit(ICompilationUnit unit) {
		this.unit = unit;
	}

	public static DummyCompilationUnit create(String prefix, IMethod method) {
		try {
			ICompilationUnit declaringUnit = method.getDeclaringType()
					.getCompilationUnit();
			IJavaElement element = declaringUnit.getParent();
			IPackageFragment fragment = AdaptableUtil.to(element,
					IPackageFragment.class);
			if (fragment != null) {
				String type = toDummyType(method);
				ICompilationUnit unit = fragment.getCompilationUnit(type
						+ ".java");
				unit = unit.getWorkingCopy(null);
				DummyCompilationUnit dummy = new DummyCompilationUnit(unit);
				dummy.dummyClassName = fragment.getElementName() + "." + type;
				dummy.implement(prefix, declaringUnit, method);
				return dummy;
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
		return null;
	}

	protected static String toDummyType(IMethod method) {
		return PREFIX_DUMMY_TYPE + method.getElementName();
	}

	public int getBefore() {
		return this.before;
	}

	public String getDummyClassName() {
		return this.dummyClassName;
	}

	protected ICompilationUnit getUnit() {
		return this.unit;
	}

	protected void implement(String prefix, ICompilationUnit declaringUnit,
			IMethod method) throws CoreException {
		StringBuilder bef = new StringBuilder();
		for (IImportDeclaration id : declaringUnit.getImports()) {
			if (Flags.isStatic(id.getFlags()) == false) {
				bef.append("import ");
				bef.append(id.getElementName());
				bef.append(";\r\n");
			}
		}
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
		if (prefix != null && 0 < prefix.length()) {
			bef.append(prefix);
		}
		this.before = bef.length();

		bef.append("}}");

		IBuffer buffer = unit.getBuffer();
		if (buffer != null) {
			buffer.setContents(bef.toString());
		}
	}

	public void codeComplete(CompletionProposalCollector collector)
			throws CoreException {
		this.unit.codeComplete(this.before, collector);
	}

}
