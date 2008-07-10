package werkzeugkasten.nlsgen.gen;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;

import werkzeugkasten.nlsgen.ResourceGenerator;

public class SingleLocaleStringsGenerator implements ResourceGenerator {

	@Override
	public boolean verifyRuntime(IJavaProject javap) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void generateFrom(IResource resource, IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

}
