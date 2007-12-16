package twowaysqleditor;

import static twowaysqleditor.rules.SqlPartitionScanner.*;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class SqlConfiguration extends SourceViewerConfiguration {

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE, SQL_IF, SQL_ELSE,
				SQL_BEGIN, SQL_END, SQL_BIND };
	}
}
