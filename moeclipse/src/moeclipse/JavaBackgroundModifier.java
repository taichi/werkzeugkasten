package moeclipse;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jdt.ui.text.folding.DefaultJavaFoldingStructureProvider;
import org.eclipse.jdt.ui.text.folding.IJavaFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.texteditor.ITextEditor;

public class JavaBackgroundModifier extends DefaultJavaFoldingStructureProvider
		implements IJavaFoldingStructureProvider {

	protected StyledText text;

	@Override
	public void install(ITextEditor editor, ProjectionViewer viewer) {
		super.install(editor, viewer);
		this.text = viewer.getTextWidget();
		text.setBackgroundImage(loadImage());
	}

	protected Image loadImage() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String path = store.getString(Constants.PREF_PICTURE_PATH);
		ImageRegistry ir = Activator.getDefault().getImageRegistry();
		ImageDescriptor id = ir.getDescriptor(path);
		if (id == null) {
			try {
				URL url = new URL(path);
				id = ImageDescriptor.createFromURL(url);
				ir.put(path, id);
			} catch (MalformedURLException e) {
				Activator.log(e);
			}
		}
		return ir.get(path);
	}

	@Override
	public void uninstall() {
		this.text.setBackgroundImage(null);
		super.uninstall();
	}

}
