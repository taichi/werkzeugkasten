package werkzeugkasten.twowaysql.editor;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;

import werkzeugkasten.common.util.Disposable;
import werkzeugkasten.common.util.Initializable;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.Constants.COLORING;
import werkzeugkasten.twowaysql.editor.conf.ColorDefineFactory;
import werkzeugkasten.twowaysql.editor.conf.ContextSettings;
import werkzeugkasten.twowaysql.editor.contentassist.CommentContentAssistProcessor;
import werkzeugkasten.twowaysql.editor.contentassist.TextContentAssistProcessor;
import werkzeugkasten.twowaysql.editor.scanner.LexerBasedColoringScanner;
import werkzeugkasten.twowaysql.editor.scanner.TextScanner;

public class TwoWaySqlConfiguration extends TextSourceViewerConfiguration
		implements Initializable, Disposable {

	protected IPreferenceStore preferenceStore;
	protected ContextSettings settings;
	protected ColorDefineFactory colorFactory;
	protected TextScanner textScanner;
	protected LexerBasedColoringScanner lineCommentScanner;
	protected LexerBasedColoringScanner blockCommentScanner;

	public TwoWaySqlConfiguration(IPreferenceStore pref,
			ContextSettings settings) {
		this.preferenceStore = pref;
		this.settings = settings;
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
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE,
				Constants.CONTENT_TYPE_TEXT,
				Constants.CONTENT_TYPE_BLOCKCOMMENT,
				Constants.CONTENT_TYPE_LINECOMMENT };
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		reconciler.setDocumentPartitioning(Constants.PARTITION_TYPE_TWOWAYSQL);
		// cf. org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getTextScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(getTextScanner());
		reconciler.setDamager(dr, Constants.CONTENT_TYPE_TEXT);
		reconciler.setRepairer(dr, Constants.CONTENT_TYPE_TEXT);

		dr = new DefaultDamagerRepairer(getBlockCommentScanner());
		reconciler.setDamager(dr, Constants.CONTENT_TYPE_BLOCKCOMMENT);
		reconciler.setRepairer(dr, Constants.CONTENT_TYPE_BLOCKCOMMENT);

		dr = new DefaultDamagerRepairer(getLineCommentScanner());
		reconciler.setDamager(dr, Constants.CONTENT_TYPE_LINECOMMENT);
		reconciler.setRepairer(dr, Constants.CONTENT_TYPE_LINECOMMENT);

		return reconciler;
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant assistant = new ContentAssistant();
		assistant.setRestoreCompletionProposalSize(Activator
				.getSettings("completion_proposal_size"));
		assistant
				.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
		assistant.setDocumentPartitioning(Constants.PARTITION_TYPE_TWOWAYSQL);
		IContentAssistProcessor processor = new TextContentAssistProcessor();
		assistant.setContentAssistProcessor(processor,
				IDocument.DEFAULT_CONTENT_TYPE);
		assistant.setContentAssistProcessor(processor,
				Constants.CONTENT_TYPE_TEXT);

		processor = new CommentContentAssistProcessor();
		assistant.setContentAssistProcessor(processor,
				Constants.CONTENT_TYPE_LINECOMMENT);
		assistant.setContentAssistProcessor(processor,
				Constants.CONTENT_TYPE_BLOCKCOMMENT);

		return assistant;
	}

	public void dispose() {
		this.colorFactory.dispose();
	}
}
