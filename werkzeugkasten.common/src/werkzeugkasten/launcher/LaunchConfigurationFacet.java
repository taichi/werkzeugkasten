package werkzeugkasten.launcher;

import org.eclipse.jdt.core.IJavaProject;


public interface LaunchConfigurationFacet {

	String getType();

	String getDescription();

	void addLibrary(IJavaProject project);

	void removeLibrary(IJavaProject project);

	LaunchConfigurationBuilder getBuilder();

}
