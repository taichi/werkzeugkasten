package werkzeugkasten.weblauncher.sdloader.job;

import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.jar.JarAssembler;
import werkzeugkasten.common.jar.ManifestModifier;
import werkzeugkasten.common.jar.URLOpener;
import werkzeugkasten.weblauncher.job.WarExportJob;
import werkzeugkasten.weblauncher.preferences.WebPreferences;
import werkzeugkasten.weblauncher.sdloader.Activator;

public class ExecutableWarExportJob extends WarExportJob {

	public ExecutableWarExportJob(IProject project) {
		super(project);
	}

	@Override
	protected void addEntries(IProgressMonitor monitor, WebPreferences pref,
			JarAssembler assembler) throws CoreException {
		assembler.add(new ManifestModifier() {
			public void modify(Manifest manifest) {
				Attributes attrs = manifest.getMainAttributes();
				attrs.put(Attributes.Name.MAIN_CLASS,
						"werkzeugkasten.weblauncher.sdloader.exec.Main");
				attrs.putValue("Server-Main", "sdloader.BrowserOpen");
				attrs.putValue("Server-lib", "sdloader-jsp20.jar");
			}
		});
		super.addEntries(monitor, pref, assembler);
		Bundle bundle = Activator.getDefault().getBundle();
		URL url = bundle.getEntry("sdloader/lib/sdloader-jsp20.jar");
		assembler.entry(new URLOpener(url), "sdloader-jsp20.jar");

		String main = "werkzeugkasten/weblauncher/sdloader/exec/Main.class";
		assembler.entry(new URLOpener(bundle.getEntry(main)), main);

	}
}
