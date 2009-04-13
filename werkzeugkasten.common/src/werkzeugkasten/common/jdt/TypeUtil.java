package werkzeugkasten.common.jdt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;

import werkzeugkasten.common.runtime.LogUtil;
import werkzeugkasten.common.util.StringUtil;

/**
 * @author taichi
 * @see org.eclipse.jdt.core.Signature
 */
public class TypeUtil {

	@SuppressWarnings("unchecked")
	public static IType resolve(Type t, IType primary, IJavaProject project) {
		IType result = null;
		try {
			if (t == null) {
			} else if (t.isArrayType()) {
				ArrayType type = (ArrayType) t;
				result = resolve(type.getComponentType(), primary, project);
			} else if (t.isParameterizedType()) {
				ParameterizedType type = (ParameterizedType) t;
				List args = type.typeArguments();
				if (args.size() == 1) {
					result = resolve((Type) args.get(0), primary, project);
				}
			} else if (t.isQualifiedType()) {
				QualifiedType type = (QualifiedType) t;
				String qn = type.getName().getIdentifier();
				result = project.findType(qn);
			} else if (t.isSimpleType()) {
				SimpleType type = (SimpleType) t;
				String qn = type.getName().getFullyQualifiedName();
				qn = TypeUtil.resolveType(qn, primary);
				result = project.findType(qn);
			}
		} catch (Exception e) {
			LogUtil.log(JavaCore.getPlugin(), e);
		}
		return result;
	}

	public static String getResolvedTypeName(String typeSignature, IType type) {
		int count = Signature.getArrayCount(typeSignature);
		if (Signature.C_UNRESOLVED == typeSignature.charAt(count)) {
			String name = null;
			int generics = typeSignature.indexOf('<');
			if (0 < generics) {
				name = typeSignature.substring(count + 1, generics);
			} else {
				if (0 < count) {
					name = typeSignature.substring(count + 1, typeSignature
							.indexOf(';'));
				} else {
					name = Signature.toString(typeSignature);
				}
			}
			return resolveType(type, count, name);
		} else {
			return Signature.toString(typeSignature);
		}
	}

	// [QMap<QLong;QDept;>;
	public static String getParameterizedTypeName(String typeSignature,
			int skip, IType type) {
		int count = Signature.getArrayCount(typeSignature);
		if (Signature.C_UNRESOLVED == typeSignature.charAt(count)) {
			String name = null;
			int generics = typeSignature.indexOf('<');
			if (0 < generics) {
				int gene2 = typeSignature.indexOf('>', generics);
				String parameterized = typeSignature.substring(generics + 1,
						gene2);
				String[] ary = parameterized.split(";");
				if (skip < ary.length) {
					int c2 = Signature.getArrayCount(ary[skip]);
					name = ary[skip].substring(c2 + 1, ary[skip].length());
				}
			} else {
				if (0 < count) {
					name = typeSignature.substring(count + 1, typeSignature
							.indexOf(';'));
				} else {
					name = Signature.toString(typeSignature);
				}
			}
			return resolveType(type, count, name);
		} else {
			return Signature.toString(typeSignature);
		}
	}

	/**
	 * @param type
	 * @param count
	 * @param shortname
	 * @return
	 * @throws JavaModelException
	 */
	private static String resolveType(IType type, int count, String shortname) {
		try {
			String[][] resolvedNames = type.resolveType(shortname);
			if (resolvedNames != null && resolvedNames.length > 0) {
				StringBuffer stb = new StringBuffer();
				String pkg = resolvedNames[0][0];
				if (pkg != null && 0 < pkg.length()) {
					stb.append(resolvedNames[0][0]);
					stb.append('.');
				}
				stb.append(resolvedNames[0][1].replace('.', '$'));
				for (int i = 0; i < count; i++) {
					stb.append("[]");
				}
				return stb.toString();
			}
		} catch (CoreException e) {
			LogUtil.log(JavaCore.getPlugin(), e);
		}
		return "";
	}

	public static String resolveType(String shortname, IType type) {
		return resolveType(type, 0, shortname);
	}

	public static List<String> getTypeNamesUnderPkg(IJavaProject project,
			String pkgName) throws CoreException {
		List<String> result = new ArrayList<String>();
		IPackageFragmentRoot[] roots = project.getAllPackageFragmentRoots();
		for (int i = 0; i < roots.length; i++) {
			IPackageFragmentRoot root = roots[i];
			if (root != null && root.exists()) {
				IPackageFragment fragment = root.getPackageFragment(pkgName);
				if (fragment != null && fragment.exists()) {
					ICompilationUnit[] classes = fragment.getCompilationUnits();
					for (int j = 0; j < classes.length; j++) {
						IType type = classes[j].findPrimaryType();
						if (type != null) {
							result.add(type.getFullyQualifiedName());
						}
					}
				}
			}
		}
		return result;
	}

	public static IMember getMember(IType type, String name)
			throws JavaModelException {
		IMember result = null;
		if (StringUtil.isEmpty(name) == false) {
			result = matchMember(type.getMethods(), name);
			if (result == null) {
				result = matchMember(type.getFields(), name);
			}
		}
		return result;
	}

	private static IMember matchMember(IMember[] members, String name) {
		for (int i = 0; i < members.length; i++) {
			if (name.equalsIgnoreCase(members[i].getElementName())) {
				return members[i];
			}
		}
		return null;
	}
}
