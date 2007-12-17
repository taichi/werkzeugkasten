package twowaysqleditor;

import static twowaysqleditor.rules.SqlPartitionScanner.*;
import static twowaysqleditor.Constants.*;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import twowaysqleditor.rules.KeywordScanner;
import twowaysqleditor.rules.NonRuleBasedDamagerRepairer;

public class SqlConfiguration extends SourceViewerConfiguration {

	protected ColorManager colorManager;
	protected KeywordScanner keywordScanner;
	protected NonRuleBasedDamagerRepairer commentScanner;

	public SqlConfiguration(ColorManager manager) {
		this.colorManager = manager;
	}

	protected KeywordScanner getKeywordScanner() {
		if (keywordScanner == null) {
			keywordScanner = new KeywordScanner(this.colorManager);
			keywordScanner.setDefaultReturnToken(new Token(new TextAttribute(
					colorManager.getColor(BLACK))));
		}
		return keywordScanner;
	}

	protected NonRuleBasedDamagerRepairer getCommentScanner() {
		if (commentScanner == null) {
			this.commentScanner = new NonRuleBasedDamagerRepairer(
					new TextAttribute(colorManager.getColor(GREEN)));
		}
		return commentScanner;
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE, SQL_IF, SQL_ELSE,
				SQL_BEGIN, SQL_END, SQL_BIND, SQL_COMMENT };
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				getKeywordScanner());
		reconciler.setDamager(dr, SQL_IF);
		reconciler.setRepairer(dr, SQL_IF);
		reconciler.setDamager(dr, SQL_ELSE);
		reconciler.setRepairer(dr, SQL_ELSE);
		reconciler.setDamager(dr, SQL_BEGIN);
		reconciler.setRepairer(dr, SQL_BEGIN);
		reconciler.setDamager(dr, SQL_END);
		reconciler.setRepairer(dr, SQL_END);

		NonRuleBasedDamagerRepairer ndr = getCommentScanner();
		reconciler.setDamager(ndr, SQL_COMMENT);
		reconciler.setRepairer(ndr, SQL_COMMENT);

		return reconciler;
	}

	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		// TODO Auto-generated method stub
		return super.getContentFormatter(sourceViewer);
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		// TODO Auto-generated method stub
		return super.getContentAssistant(sourceViewer);
	}
}
