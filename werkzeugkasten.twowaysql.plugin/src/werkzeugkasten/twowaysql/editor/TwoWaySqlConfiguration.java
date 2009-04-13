package werkzeugkasten.twowaysql.editor;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IUndoManager;
import org.eclipse.jface.text.TextViewerUndoManager;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.URLHyperlinkDetector;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.Annotation;
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
import werkzeugkasten.twowaysql.editor.formatter.StateBasedSelectableContentFormatter;
import werkzeugkasten.twowaysql.editor.hyperlink.ELHyperlinkDetector;
import werkzeugkasten.twowaysql.editor.reconciler.TwoWaySqlReconcilingStrategy;
import werkzeugkasten.twowaysql.editor.scanner.LexerBasedColoringScanner;
import werkzeugkasten.twowaysql.editor.scanner.TextScanner;

public class TwoWaySqlConfiguration extends TextSourceViewerConfiguration
		implements Initializable, Disposable {

	protected ContextSettings settings;
	protected ColorDefineFactory colorFactory;
	protected TextScanner textScanner;
	protected LexerBasedColoringScanner lineCommentScanner;
	protected LexerBasedColoringScanner blockCommentScanner;
	protected TwoWaySqlEditor sqlEditor;

	public TwoWaySqlConfiguration(TwoWaySqlEditor editor,
			IPreferenceStore pref, ContextSettings settings) {
		super(pref);
		this.sqlEditor = editor;
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

		// ProblemAnnotationにTextHoverを表示するスイッチ
		this.fPreferenceStore.setDefault("errorIndication", true);

		// 文字列が、HyperLink化した時の色
		this.fPreferenceStore.setDefault("hyperlinkColor", "0,0,255");
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
		assistant.enableAutoActivation(true);
		assistant.setDocumentPartitioning(Constants.PARTITION_TYPE_TWOWAYSQL);
		IContentAssistProcessor processor = new TextContentAssistProcessor();
		assistant.setContentAssistProcessor(processor,
				IDocument.DEFAULT_CONTENT_TYPE);
		assistant.setContentAssistProcessor(processor,
				Constants.CONTENT_TYPE_TEXT);

		processor = new CommentContentAssistProcessor(this.sqlEditor,
				this.settings);
		assistant.setContentAssistProcessor(processor,
				Constants.CONTENT_TYPE_LINECOMMENT);
		assistant.setContentAssistProcessor(processor,
				Constants.CONTENT_TYPE_BLOCKCOMMENT);

		return assistant;
	}

	/**
	 * @see org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration#getReconciler
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy
	 */
	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		MonoReconciler reconciler = new MonoReconciler(
				new TwoWaySqlReconcilingStrategy(sourceViewer), false);
		reconciler.setIsAllowedToModifyDocument(false);
		reconciler.setDelay(500);
		return reconciler;
	}

	@Override
	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
		if (sourceViewer == null) {
			return null;
		}
		return new IHyperlinkDetector[] { new URLHyperlinkDetector(),
				new ELHyperlinkDetector(this.sqlEditor, this.settings) };
	}

	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		if (sourceViewer == null) {
			return null;
		}
		return new StateBasedSelectableContentFormatter(sourceViewer);
	}

	/*
	 * default editor configurations.
	 * IPreferenceStoreをTextSourceViewerConfigurationに食わせる事で、
	 * 設定を抜こうと頑張るが上手く取れないケースに対応しちる。
	 */

	@Override
	protected boolean isShowInVerticalRuler(Annotation annotation) {
		return true;
	}

	@Override
	protected boolean isShowInOverviewRuler(Annotation annotation) {
		return true;
	}

	@Override
	public int getTabWidth(ISourceViewer sourceViewer) {
		return 4;
	}

	@Override
	public IUndoManager getUndoManager(ISourceViewer sourceViewer) {
		return new TextViewerUndoManager(25);
	}

	public void dispose() {
		this.colorFactory.dispose();
	}
}
