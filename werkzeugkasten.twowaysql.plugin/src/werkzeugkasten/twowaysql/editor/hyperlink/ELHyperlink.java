package werkzeugkasten.twowaysql.editor.hyperlink;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.ui.PartInitException;

import werkzeugkasten.twowaysql.Activator;

public class ELHyperlink implements IHyperlink {

	protected IRegion region;
	protected IJavaElement target;

	public ELHyperlink(IRegion region, IJavaElement element) {
		Assert.isNotNull(region);
		Assert.isNotNull(element);
		this.region = region;
		this.target = element;
	}

	@Override
	public IRegion getHyperlinkRegion() {
		return this.region;
	}

	@Override
	public String getHyperlinkText() {
		try {
			return this.target.getAttachedJavadoc(null);
		} catch (JavaModelException e) {
			Activator.log(e);
		}
		return null;
	}

	@Override
	public String getTypeLabel() {
		return ELHyperlink.class.getSimpleName();
	}

	@Override
	public void open() {
		try {
			JavaUI.openInEditor(this.target);
		} catch (PartInitException e) {
			Activator.log(e);
		} catch (JavaModelException e) {
			Activator.log(e);
		}
	}

}
