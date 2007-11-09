package werkzeugkasten.launcher;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;

public interface LaunchConfigurationFacet {

	String getType();

	String getDescription();

	boolean hasLibrary(IJavaProject project) throws CoreException;

	void addLibrary(IJavaProject project) throws CoreException;

	void removeLibrary(IJavaProject project) throws CoreException;

	LaunchConfigurationBuilder getBuilder();

}
