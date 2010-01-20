package moeclipse;

import org.eclipse.jdt.ui.text.folding.DefaultJavaFoldingStructureProvider;
import org.eclipse.jdt.ui.text.folding.IJavaFoldingStructureProvider;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.texteditor.ITextEditor;

public class JavaBackgroundModifier extends DefaultJavaFoldingStructureProvider
		implements IJavaFoldingStructureProvider {

	protected StyledText text;

	@Override
	public void install(ITextEditor editor, ProjectionViewer viewer) {
		super.install(editor, viewer);
		this.text = viewer.getTextWidget();
		text.setBackgroundImage(null);
	}

	@Override
	public void uninstall() {
		this.text.setBackgroundImage(null);
		super.uninstall();
	}

}
