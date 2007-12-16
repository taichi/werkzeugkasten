package twowaysqleditor;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IMethod;

public interface EditorContext {

	IFile getSqlFile();

	IMethod getMethod();

	Set<String> getArgNames();
}
