package werkzeugkasten.editor;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import werkzeugkasten.common.resource.LogUtil;
import werkzeugkasten.common.ui.WorkbenchUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The shared instance
	private static Activator plugin;

	protected StateHolder holder = new StateHolder();

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		update(getCheckedFromPref());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void log(Throwable throwable) {
		LogUtil.log(getDefault(), throwable);
	}

	public static boolean getCheckedFromPref() {
		boolean checked = false;
		Preferences store = Activator.getDefault().getPluginPreferences();
		if (store != null) {
			checked = store.getBoolean(Constants.PREF_SHOW_WHITESPACE);
		}
		return checked;
	}

	public ITextViewer getActiveTextViewer() {
		IEditorPart part = WorkbenchUtil.getActiveEditor();
		return holder.getTextViewer(holder.to(holder.to(part)));
	}

	public void update(boolean isChecked) {
		holder.setChecked(isChecked);
		Preferences store = getPluginPreferences();
		if (store != null) {
			store.setValue(Constants.PREF_SHOW_WHITESPACE, isChecked);
		}
		savePluginPreferences();
	}

	public void addPainter(IWorkbenchPartReference partRef) {
		this.holder.addPainter(partRef);
	}

	public void addToAllEditors() {
		this.holder.addToAllEditors();
	}

	public void removePainter(IWorkbenchPartReference partRef) {
		this.holder.removePainter(partRef);
	}

	public void removeFromAllEditors() {
		this.holder.removeFromAllEditors();
	}
}
