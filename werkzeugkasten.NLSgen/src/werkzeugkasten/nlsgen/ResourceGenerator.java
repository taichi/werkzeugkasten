package werkzeugkasten.nlsgen;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;

public interface ResourceGenerator {

	void generateFrom(IResource resource, IProgressMonitor monitor);

	boolean verifyRuntime(IJavaProject javap);
}
