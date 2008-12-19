package werkzeugkasten.twowaysql.editor;

import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;

import werkzeugkasten.common.util.Disposable;
import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.editor.conf.ColorDefineFactory;

public class TwoWaySqlConfiguration extends TextSourceViewerConfiguration
		implements Disposable {

	protected ColorDefineFactory colorFactory;

	public TwoWaySqlConfiguration() {
		this.colorFactory = new ColorDefineFactory();
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		reconciler.setDocumentPartitioning(Constants.CT_TWOWAYSQL);

		return reconciler;
	}

	public void dispose() {
		this.colorFactory.dispose();
	}
}
