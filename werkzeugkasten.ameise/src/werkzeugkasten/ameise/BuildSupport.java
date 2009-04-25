package werkzeugkasten.ameise;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import werkzeugkasten.common.util.FileUtil;

public abstract class BuildSupport {

	protected static FileUtil.PathFilter FILTER_DEFAULT_IGNORED = new FileUtil.ReverseFilter(
			new FileUtil.PatternFilter(
					"^(\\..*|CVS|SCCS|[Vv][Ss][Ss][Vv][Ee][Rr]\\.[Ss][Cc][Cc]|.*(\\.([Bb][Aa]?[Kk]|[Tt][Mm][Pp]|[Oo][Rr][Ii]?[Gg])|~))$"));

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface Task {
		String description();
	}

	public void all() throws Exception {

	}

	protected Map<String, Method> getTaskMethods() {
		Map<String, Method> result = new HashMap<String, Method>();
		for (Method m : getClass().getDeclaredMethods()) {
			int mod = m.getModifiers();
			if (Modifier.isStatic(mod) == false && Modifier.isPublic(mod)
					&& m.getParameterTypes().length < 1) {
				result.put(m.getName(), m);
			}
		}
		return result;
	}

	/**
	 * download from artifact from maven2 repository.
	 * 
	 * @param groupId
	 * @param artifactId
	 * @param version
	 */
	protected void depends(String groupId, String artifactId, String version) {

	}

	/**
	 * list files with default ignored filter
	 * 
	 * @param path
	 *            listing directory or file
	 * @return files
	 */
	protected List<File> list(String path) {
		return FileUtil.list(path, FILTER_DEFAULT_IGNORED);
	}

	/**
	 * list files with filter
	 * 
	 * @param path
	 *            listing directory or file
	 * @param filter
	 * @return files
	 */
	protected List<File> list(String path, FileUtil.PathFilter filter) {
		return FileUtil.list(path, filter);
	}

	protected void zip(String dest, String root) {

	}

	protected void mkdirs(String path) {

	}

	protected void copy(String from, String to) {
		copy(from, to, FILTER_DEFAULT_IGNORED);
	}

	protected void copy(String from, String to, FileUtil.PathFilter filter) {
		File f = new File(from);
		File t = new File(to);
		if (f.exists()) {
			if (f.isFile()) {
				if (t.exists()) {
					delete(t);
				}
				FileUtil.copy(FileUtil.open(f), t);
			}
		}
	}

	protected void delete(File file) {
		FileUtil.delete(file);
	}

	protected void delete(String root, FileUtil.PathFilter filter) {
		FileUtil.delete(root, filter);
	}

	protected void javac() {

	}

	protected void javadoc() {
	}

	protected void junit(Class<?> testCase) {

	}
}
