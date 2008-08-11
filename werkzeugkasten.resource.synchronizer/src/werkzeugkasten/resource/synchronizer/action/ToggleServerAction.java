/*
 * Copyright 2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package werkzeugkasten.resource.synchronizer.action;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.seasar.eclipse.common.action.EnablerAction;

import werkzeugkasten.resource.synchronizer.Activator;
import werkzeugkasten.resource.synchronizer.job.StartServerJob;
import werkzeugkasten.resource.synchronizer.job.StopServerJob;
import werkzeugkasten.resource.synchronizer.nls.Images;
import werkzeugkasten.resource.synchronizer.nls.Strings;

/**
 * 
 * @author taichi
 * 
 */
public class ToggleServerAction extends EnablerAction {

	private interface Strategy {

		ImageDescriptor getImage();

		String getText();

		void run() throws CoreException;

	}

	private static class Start implements Strategy {

		public ImageDescriptor getImage() {
			return Images.START;
		}

		public String getText() {
			return Strings.LABEL_START;
		}

		public void run() throws CoreException {
			new StartServerJob().schedule();
		}
	}

	private static class Stop implements Strategy {

		public ImageDescriptor getImage() {
			return Images.STOP;
		}

		public String getText() {
			return Strings.LABEL_STOP;
		}

		public void run() throws CoreException {
			new StopServerJob().schedule();
		}
	}

	private Strategy start;

	private Strategy stop;

	private Strategy current;

	public ToggleServerAction() {
		start = new Start();
		stop = new Stop();
		current = start;
	}

	@Override
	public void init(IWorkbenchWindow window) {
		super.init(window);
		Activator.setToggleAction(this);
	}

	@Override
	public synchronized boolean checkEnabled() {
		if (Activator.isRunning()) {
			current = stop;
		} else {
			current = start;
		}
		delegate.setText(current.getText());
		delegate.setImageDescriptor(current.getImage());
		return true;
	}

	public void run(IAction action) {
		try {
			current.run();
			if (current == start) {
				current = stop;
			} else {
				current = start;
			}
			action.setImageDescriptor(current.getImage());
			action.setText(current.getText());
		} catch (CoreException e) {
			Activator.log(e);
		}
	}

}
