package werkzeugkasten.resource.synchronizer.wizard;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;

import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.resource.ResourceUtil;
import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.resource.synchronizer.Activator;
import werkzeugkasten.resource.synchronizer.nls.Strings;

/**
 * @author taichi
 */
public class DebugJspCreationWizard extends BasicNewResourceWizard implements
		IWizard {

	public static final String NAME = DebugJspCreationWizard.class.getName();

	protected DebugJspCreationWizardPage creationWizardPage;

	protected IProject project;

	public DebugJspCreationWizard() {
		setDialogSettings(Activator.getSettings());
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		setWindowTitle(Strings.TITLE_NEW_DEBUG_JSP);
		setNeedsProgressMonitor(true);

		this.project = AdaptableUtil.to(currentSelection.getFirstElement(),
				IProject.class);
	}

	@Override
	public void addPages() {
		this.creationWizardPage = new DebugJspCreationWizardPage(this.project);
		this.creationWizardPage.setDescription(Strings.MSG_NEW_DEBUG_JSP);
		addPage(this.creationWizardPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		IPath root = new Path(this.creationWizardPage.getContextRoot());
		IPath out = new Path(this.creationWizardPage.getOutputDir());
		IWorkspaceRoot wr = ResourcesPlugin.getWorkspace().getRoot();
		ResourceUtil.createDir(wr, out.toString());
		final IFolder folder = wr.getFolder(out);
		if (folder == null) {
			return false;
		}
		final String errorScreenPath = "/"
				+ out.removeFirstSegments(root.segmentCount()).toString();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			@SuppressWarnings("unchecked")
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				monitor.beginTask(Strings.MSG_CREATE_CONTENTS, 0);
				Bundle bundle = Activator.getDefault().getBundle();
				for (Enumeration e = bundle
						.findEntries("debug-info", "*", true); e
						.hasMoreElements();) {
					URL url = (URL) e.nextElement();
					String path = url.getPath();
					if (path.contains(".svn") == false) {
						IPath p = new Path(path).removeFirstSegments(1);
						if (path.endsWith("/")) {
							ResourceUtil.createDir(folder, p.toString());
						} else {
							IFile file = folder.getFile(p);
							if (file.exists() == false
									&& file.getName().startsWith(".") == false) {
								try {
									InputStream stream = new BufferedInputStream(
											url.openStream());
									try {
										file.create(stream, true, monitor);
									} finally {
										stream.close();
									}
									if ("debug.jsp".equals(p.lastSegment())) {
										processJsp(errorScreenPath, monitor,
												file.getFullPath());
									}
								} catch (Exception ex) {
									throw new InvocationTargetException(ex);
								}
							}
						}
					}
				}
			}
		};
		try {
			getContainer().run(true, false, op);
			return true;
		} catch (Exception e) {
			Activator.log(e);
		}
		return false;
	}

	protected void processJsp(final String errorScreenPath,
			IProgressMonitor monitor, IPath p) throws CoreException {
		ITextFileBufferManager.DEFAULT.connect(p, LocationKind.IFILE, monitor);
		try {
			ITextFileBuffer buf = ITextFileBufferManager.DEFAULT
					.getTextFileBuffer(p, LocationKind.IFILE);
			try {
				IDocument doc = buf.getDocument();
				FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(
						doc);
				finder.find(0, "###ERROR_SCRREN###", true, true, false, true);
				finder.replace(errorScreenPath, false);
				finder.find(0, "###PROJECT###", true, true, false, true);
				finder.replace(project.getName(), false);
				buf.commit(monitor, true);
			} catch (Exception ex) {
				buf.revert(monitor);
			}
		} finally {
			ITextFileBufferManager.DEFAULT.disconnect(p, LocationKind.IFILE,
					monitor);
		}
	}
}
