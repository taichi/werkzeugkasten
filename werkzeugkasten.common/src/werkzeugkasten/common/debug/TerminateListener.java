package werkzeugkasten.common.debug;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;

import werkzeugkasten.common.runtime.LogUtil;

/**
 * @author taichi
 * 
 */
public abstract class TerminateListener implements IDebugEventSetListener {

	/**
	 * 
	 */
	public TerminateListener() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.IDebugEventSetListener#handleDebugEvents(org.eclipse.debug.core.DebugEvent[])
	 */
	public void handleDebugEvents(DebugEvent[] events) {
		for (int i = 0; i < events.length; i++) {
			DebugEvent event = events[i];
			if (event.getKind() == DebugEvent.TERMINATE) {
				Object o = event.getSource();
				if (o instanceof IProcess) {
					try {
						ILaunch l = ((IProcess) o).getLaunch();
						handle(l);
					} catch (CoreException e) {
						LogUtil.log(ResourcesPlugin.getPlugin(), e);
					}
				}
			}
		}
	}

	public abstract void handle(ILaunch l) throws CoreException;

}
