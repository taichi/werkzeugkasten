package werkzeugkasten.common.viewers;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.ui.IDecoratorManager;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractLightweightLabelDecorator extends LabelProvider
		implements ILightweightLabelDecorator {

	public static void updateDecorators(String decoratorId, Object element) {
		if (element == null) {
			return;
		}
		IWorkbench workbench = PlatformUI.getWorkbench();
		IDecoratorManager manager = workbench.getDecoratorManager();
		IBaseLabelProvider provider = manager.getBaseLabelProvider(decoratorId);
		if (provider instanceof AbstractLightweightLabelDecorator) {
			final AbstractLightweightLabelDecorator decorator = (AbstractLightweightLabelDecorator) provider;
			final LabelProviderChangedEvent event = new LabelProviderChangedEvent(
					decorator, element);
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					decorator.fireLabelProviderChanged(event);
				}
			});
		}
	}
}
