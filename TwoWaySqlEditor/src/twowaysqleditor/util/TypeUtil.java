package twowaysqleditor.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;

import twowaysqleditor.Activator;

/**
 * @author taichi
 * 
 */
public class TypeUtil {

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
	 * @param dimension
	 * @param shortname
	 * @return
	 * @throws JavaModelException
	 */
	private static String resolveType(IType type, int dimension,
			String shortname) {
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
				for (int i = 0; i < dimension; i++) {
					stb.append("[]");
				}
				return stb.toString();
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
		return "";
	}

	public static String resolveType(String shortname, IType type) {
		return resolveType(type, 0, shortname);
	}
}
