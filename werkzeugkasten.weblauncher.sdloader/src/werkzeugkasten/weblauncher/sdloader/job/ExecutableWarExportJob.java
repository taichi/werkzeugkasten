package werkzeugkasten.weblauncher.sdloader.job;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.jar.JarAssembler;
import werkzeugkasten.common.jar.ManifestModifier;
import werkzeugkasten.common.jar.Opener;
import werkzeugkasten.common.jar.URLOpener;
import werkzeugkasten.common.runtime.StatusUtil;
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
		try {
			Bundle bundle = Activator.getDefault().getBundle();
			URL url = bundle.getEntry("sdloader/lib/sdloader-jsp20.jar");
			assembler.entry(new URLOpener(url), "sdloader-jsp20.jar");

			url = bundle.getEntry("sdloader/lib/executablewar.jar");
			final JarFile jar = new JarFile(url.getPath());
			final JarEntry entry = jar
					.getJarEntry("werkzeugkasten/weblauncher/sdloader/exec/Main.class");
			assembler.entry(new Opener() {
				public InputStream open() throws Exception {
					return jar.getInputStream(entry);
				}
			}, "werkzeugkasten/weblauncher/sdloader/exec/Main.class");

		} catch (IOException e) {
			IStatus s = StatusUtil.createError(Activator.getDefault(), e);
			throw new CoreException(s);
		}
	}
}
