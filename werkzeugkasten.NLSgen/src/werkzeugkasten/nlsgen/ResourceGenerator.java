package werkzeugkasten.nlsgen;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;

public interface ResourceGenerator {

	void generateFrom(IResource resource, IProgressMonitor monitor);
}
