package werkzeugkasten.common.debug;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.sourcelookup.SourceLookupTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaArgumentsTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaClasspathTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaJRETab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaMainTab;

/**
 * @author taichi
 * 
 */
public class BasicLaunchConfigurationTabGroup extends
		AbstractLaunchConfigurationTabGroup {

	/**
	 * 
	 */
	public BasicLaunchConfigurationTabGroup() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
	 *      java.lang.String)
	 */
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
				new JavaMainTab(), new JavaArgumentsTab(), new JavaJRETab(),
				new JavaClasspathTab(), new SourceLookupTab(),
				new EnvironmentTab(), new CommonTab() };
		for (ILaunchConfigurationTab tab : tabs) {
			tab.setLaunchConfigurationDialog(dialog);
		}
		setTabs(tabs);
	}
}
