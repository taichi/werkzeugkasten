package werkzeugkasten.weblauncher.sdloader.job;

import java.io.IOException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.ui.jarpackager.IJarBuilder;
import org.eclipse.jdt.ui.jarpackager.JarPackageData;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.runtime.StatusUtil;
import werkzeugkasten.weblauncher.job.WarExportJob;
import werkzeugkasten.weblauncher.preferences.WebPreferences;
import werkzeugkasten.weblauncher.sdloader.Activator;

public class ExecutableWarExportJob extends WarExportJob {

	public ExecutableWarExportJob(IProject project) {
		super(project);
	}

	protected void addEntries(final IProgressMonitor monitor,
			WebPreferences pref, JarPackageData data, final IJarBuilder builder)
			throws CoreException {
		data.setManifestProvider(new ManifestProvider() {
			protected void putAdditionalEntries(Manifest manifest,
					JarPackageData jarPackage) {
				Attributes attrs = manifest.getMainAttributes();
				attrs.put(Attributes.Name.MAIN_CLASS,
						"werkzeugkasten.weblauncher.sdloader.exec.Main");
				attrs.putValue("Server-Main", "sdloader.BrowserOpen");
				attrs.putValue("Server-lib", "sdloader-jsp20.jar");
			}
		});
		super.addEntries(monitor, pref, data, builder);
		try {
			Bundle bundle = Activator.getDefault().getBundle();
			builder.writeFile(null, new Path(".")); // sdloader-jsp20.jarを置きたい…

			URL url = bundle.getEntry("sdloader/lib/executablewar.jar");
			ZipFile archive = new ZipFile(url.getPath());
			builder.writeArchive(archive, monitor);
		} catch (IOException e) {
			IStatus s = StatusUtil.createError(Activator.getDefault(), e);
			throw new CoreException(s);
		}
	};
}
