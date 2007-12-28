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
		StringBuilder stb = new StringBuilder();
		for (IImportDeclaration id : declaringUnit.getImports()) {
			if (Flags.isStatic(id.getFlags()) == false) {
				stb.append("import ");
				stb.append(id.getElementName());
				stb.append(";\r\n");
			}
		}
		stb.append("public class ");
		stb.append(toDummyType(method));
		stb.append("{\r\n");
		stb.append(Flags.toString(method.getFlags()));
		stb.append(" ");
		stb.append(Signature.toString(method.getReturnType()));
		stb.append(" ");
		stb.append(method.getElementName());
		stb.append("(");
		String[] types = method.getParameterTypes();
		String[] names = method.getParameterNames();
		if (types != null && names != null && 0 < types.length
				&& 0 < names.length) {
			for (int i = 0; i < types.length; i++) {
				stb.append(Signature.toString(types[i]));
				stb.append(" ");
				stb.append(names[i]);
				stb.append(",");
			}
			stb.setLength(stb.length() - 1);
		}
		stb.append(") throws Exception{\r\n");
		if (prefix != null && 0 < prefix.length()) {
			stb.append(prefix);
		}
		this.before = stb.length();

		stb.append("}}");

		IBuffer buffer = unit.getBuffer();
		if (buffer != null) {
			buffer.setContents(stb.toString());
		}
	}

	public void codeComplete(CompletionProposalCollector collector)
			throws CoreException {
		this.unit.codeComplete(this.before, collector);
	}

}
