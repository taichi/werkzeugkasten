package werkzeugkasten.nlsgen.listener;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;

import werkzeugkasten.nlsgen.job.NLSClassGenJob;

public class PropertiesChangeListener implements IResourceChangeListener {

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		int flag = IResourceDelta.ADDED | IResourceDelta.CHANGED;
		if (delta != null && ((delta.getKind() & flag) != 0)) {
			new NLSClassGenJob(delta).schedule();
		}
	}
}
