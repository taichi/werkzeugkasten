package werkzeugkasten.weblauncher.job;

import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.ui.jarpackager.IJarBuilder;
import org.eclipse.jdt.ui.jarpackager.JarPackageData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.WorkbenchJob;

import werkzeugkasten.common.ui.ProgressMonitorUtil;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;

public class WarExportJob extends WorkbenchJob {

	protected JarPackageData data;
	protected IPath basePath;
	protected IResource base;

	public WarExportJob(JarPackageData data, IPath basePath) {
		super("WarExportJob"); // TODO externalize strings.
		this.data = data;
		this.basePath = basePath;
		this.base = ResourcesPlugin.getWorkspace().getRoot().findMember(
				basePath);
	}

	public IStatus runInUIThread(final IProgressMonitor monitor) {
		monitor.beginTask("Export War ...", IProgressMonitor.UNKNOWN);
		try {

			final MultiStatus status = new MultiStatus(Constants.ID_PLUGIN,
					IStatus.ERROR, "War Export Errors", null); // TODO to
			// externalize
			// strings.
			final IJarBuilder builder = data.createFatJarBuilder();
			final int baseSegment = basePath.segmentCount();
			try {
				data.setElements(new Object[] { base }); // dummy data.
				builder.open(data, new Shell(getDisplay()), status);

				final Pattern ignore = Pattern.compile("\\.(bak|tmp|ori?g)",
						Pattern.CASE_INSENSITIVE); // TODO to be Pref.
				base.accept(new IResourceVisitor() {
					public boolean visit(IResource resource)
							throws CoreException {
						ProgressMonitorUtil.isCanceled(monitor, 1);
						switch (resource.getType()) {
						case IResource.FOLDER: {
							return resource.getName().startsWith(".") == false;
						}
						case IResource.FILE: {
							IFile f = (IFile) resource;
							String name = f.getName();
							if (ignore.matcher(name).matches()) {
								return false;
							}
							IPath dest = f.getFullPath().removeFirstSegments(
									baseSegment);
							builder.writeFile(f, dest);
							break;
						}
						default: {
							break;
						}
						}
						return true;
					}
				});
				ProgressMonitorUtil.isCanceled(monitor, 1);
				IStatus[] kids = status.getChildren();
				if (kids != null && 0 < kids.length) {
					Activator.getDefault().getLog().log(status);
				}
				ProgressMonitorUtil.isCanceled(monitor, 1);
			} finally {
				builder.close();
			}
		} catch (OperationCanceledException e) {
			throw e;
		} catch (Exception e) {
			Activator.log(e);
		} finally {
			monitor.done();
		}
		return Status.OK_STATUS;
	}

}
