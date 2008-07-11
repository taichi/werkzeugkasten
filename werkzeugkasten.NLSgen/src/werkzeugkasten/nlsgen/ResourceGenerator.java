package werkzeugkasten.nlsgen;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;

public interface ResourceGenerator {

	boolean verifyRuntime(IJavaProject javap);

	void addRuntime(IContainer container);

	void generateFrom(IFile properties, IProgressMonitor monitor);
}
