package twowaysqleditor;

import static twowaysqleditor.Constants.*;
import static twowaysqleditor.rules.SqlPartitionScanner.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import twowaysqleditor.formatter.OneShotContentFormatter;
import twowaysqleditor.rules.KeywordScanner;
import twowaysqleditor.rules.NonRuleBasedDamagerRepairer;
import twowaysqleditor.rules.SqlPartitionScanner;

public class SqlConfiguration extends SourceViewerConfiguration {

	protected ColorManager colorManager;
	protected KeywordScanner keywordScanner;
	protected NonRuleBasedDamagerRepairer commentScanner;
	protected IContentFormatter contentFormatter;

	public SqlConfiguration(ColorManager manager) {
		this.colorManager = manager;
	}

	protected KeywordScanner getKeywordScanner() {
		if (keywordScanner == null) {
			keywordScanner = new KeywordScanner(this.colorManager);
			keywordScanner.setDefaultReturnToken(new Token(new TextAttribute(
					colorManager.getColor(GREEN))));
		}
		return keywordScanner;
	}

	protected NonRuleBasedDamagerRepairer getCommentScanner() {
		if (commentScanner == null) {
			commentScanner = new NonRuleBasedDamagerRepairer(new TextAttribute(
					colorManager.getColor(GREEN)));
		}
		return commentScanner;
	}

	protected IContentFormatter getContentFormatter() {
		if (contentFormatter == null) {
			contentFormatter = new OneShotContentFormatter();
		}
		return contentFormatter;
	}

	protected static final String[] CONTENT_TYPES;
	static {
		List<String> list = new ArrayList<String>();
		list.add(IDocument.DEFAULT_CONTENT_TYPE);
		list.addAll(Arrays.asList(SqlPartitionScanner.PARTITIONS));
		CONTENT_TYPES = list.toArray(new String[list.size()]);
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return CONTENT_TYPES;
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				getKeywordScanner());
		for (String type : new String[] { SQL_IF, SQL_ELSE, SQL_BEGIN, SQL_END }) {
			reconciler.setDamager(dr, type);
			reconciler.setRepairer(dr, type);
		}

		NonRuleBasedDamagerRepairer ndr = getCommentScanner();
		reconciler.setDamager(ndr, SQL_COMMENT);
		reconciler.setRepairer(ndr, SQL_COMMENT);

		return reconciler;
	}

	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		return getContentFormatter();
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant assistant = new ContentAssistant();

		return assistant;
	}
}
