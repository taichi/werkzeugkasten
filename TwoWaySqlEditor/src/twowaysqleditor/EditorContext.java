package twowaysqleditor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;

public class EditorContext {

	protected IFile sqlFile;

	protected IMethod method;

	public EditorContext() {
	}

	public void setSqlFile(IFile sqlFile) {
		this.sqlFile = sqlFile;
		try {
			this.method = calcMethod(sqlFile);
		} catch (CoreException e) {
			Activator.log(e);
		}
	}

	public IFile getSqlFile() {
		return this.sqlFile;
	}

	public IMethod getMethod() {
		return this.method;
	}

	public Set<String> getArgNames() {
		Set<String> result = null;
		try {
			result = doGetArgNames();
			if (result == null) {
				this.method = calcMethod(sqlFile);
				result = doGetArgNames();
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
		return result;
	}

	protected Set<String> doGetArgNames() throws CoreException {
		if (method != null && method.exists()) {
			Set<String> names = new HashSet<String>(Arrays.asList(method
					.getParameterNames()));
			return names;
		}
		return null;
	}

	private static IMethod calcMethod(IFile sqlFile) throws CoreException {
		// TODO load from persistant property
		if (sqlFile != null && sqlFile.exists()) {
			IPath sqlPkg = sqlFile.getFullPath().removeLastSegments(1);
			IJavaProject javap = JavaCore.create(sqlFile.getProject());
			IPackageFragment fragment = javap.findPackageFragment(sqlPkg);
			if (fragment != null && fragment.exists()) {
				String filename = sqlFile.getName();
				int atFirst = filename.indexOf('_');
				if (-1 < atFirst) {
					String unitname = filename.substring(0, atFirst) + ".java";
					int dbtype = filename.indexOf('_', atFirst + 1);
					if (dbtype < atFirst) {
						dbtype = filename.indexOf('.', atFirst + 1);
					}
					if (atFirst < dbtype) {
						String methodname = filename.substring(atFirst + 1,
								dbtype);
						for (IPackageFragmentRoot root : javap
								.getAllPackageFragmentRoots()) {
							IPackageFragment pkg = root
									.getPackageFragment(fragment
											.getElementName());
							if (pkg != null && pkg.exists()) {
								ICompilationUnit unit = pkg
										.getCompilationUnit(unitname);
								if (unit != null && unit.exists()) {
									IType type = unit.findPrimaryType();
									for (IMethod m : type.getMethods()) {
										if (methodname.equalsIgnoreCase(m
												.getElementName())) {
											return m;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
}
