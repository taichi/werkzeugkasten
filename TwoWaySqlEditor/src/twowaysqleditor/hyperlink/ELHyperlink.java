package twowaysqleditor.hyperlink;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;

import twowaysqleditor.Activator;

public class ELHyperlink implements IHyperlink {

	protected IRegion region;
	protected IJavaElement target;

	public ELHyperlink(IRegion region, IJavaElement target) {
		this.region = region;
		this.target = target;
	}

	public IRegion getHyperlinkRegion() {
		return this.region;
	}

	public String getHyperlinkText() {
		return null;
	}

	public String getTypeLabel() {
		return null;
	}

	public void open() {
		try {
			JavaUI.openInEditor(target);
		} catch (CoreException e) {
			Activator.log(e);
		}
	}

}
