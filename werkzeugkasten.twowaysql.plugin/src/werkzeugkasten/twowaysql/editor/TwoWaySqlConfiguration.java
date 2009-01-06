package werkzeugkasten.twowaysql.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;

import werkzeugkasten.common.util.Disposable;
import werkzeugkasten.common.util.Initializable;
import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.Constants.COLORING;
import werkzeugkasten.twowaysql.editor.conf.ColorDefineFactory;
import werkzeugkasten.twowaysql.editor.scanner.LexerBasedColoringScanner;
import werkzeugkasten.twowaysql.editor.scanner.TextScanner;

public class TwoWaySqlConfiguration extends TextSourceViewerConfiguration
		implements Initializable, Disposable {

	protected ColorDefineFactory colorFactory;
	protected TextScanner textScanner;
	protected LexerBasedColoringScanner lineCommentScanner;
	protected LexerBasedColoringScanner blockCommentScanner;

	public TwoWaySqlConfiguration() {
		this.colorFactory = new ColorDefineFactory();
		this.colorFactory.initialize();
	}

	@Override
	public void initialize() {
		this.textScanner = new TextScanner(getColorFactory());
		this.textScanner.initialize();
		this.lineCommentScanner = new LexerBasedColoringScanner(
				COLORING.COMMENT, getColorFactory());
		this.blockCommentScanner = new LexerBasedColoringScanner(
				COLORING.COMMENT, getColorFactory());
	}

	protected ColorDefineFactory getColorFactory() {
		return colorFactory;
	}

	protected ITokenScanner getTextScanner() {
		return textScanner;
	}

	protected ITokenScanner getLineCommentScanner() {
		return lineCommentScanner;
	}

	protected ITokenScanner getBlockCommentScanner() {
		return blockCommentScanner;
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		reconciler.setDocumentPartitioning(Constants.CT_TWOWAYSQL);
		// cf. org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getTextScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(getTextScanner());
		reconciler.setDamager(dr, Constants.CT_TEXT);
		reconciler.setRepairer(dr, Constants.CT_TEXT);

		dr = new DefaultDamagerRepairer(getLineCommentScanner());
		reconciler.setDamager(dr, Constants.CT_LINECOMMENT);
		reconciler.setRepairer(dr, Constants.CT_LINECOMMENT);

		dr = new DefaultDamagerRepairer(getBlockCommentScanner());
		reconciler.setDamager(dr, Constants.CT_BLOCKCOMMENT);
		reconciler.setRepairer(dr, Constants.CT_BLOCKCOMMENT);

		return reconciler;
	}

	public void dispose() {
		this.colorFactory.dispose();
	}
}
