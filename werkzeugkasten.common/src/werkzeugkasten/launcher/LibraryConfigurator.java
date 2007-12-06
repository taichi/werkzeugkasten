package werkzeugkasten.launcher;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;

public interface LibraryConfigurator extends ConfigurationFacet {

	boolean hasLibrary(IJavaProject project) throws CoreException;

	void addLibrary(IJavaProject project) throws CoreException;

	void removeLibrary(IJavaProject project) throws CoreException;

}
