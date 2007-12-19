package twowaysqleditor.hyperlink;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;

import twowaysqleditor.Constants;
import twowaysqleditor.rules.SqlPartitionScanner;
import twowaysqleditor.util.DocumentUtil;

public class ELHyperlinkDetector extends AbstractHyperlinkDetector {

	public IHyperlink[] detectHyperlinks(ITextViewer textViewer,
			IRegion region, boolean canShowMultipleHyperlinks) {
		if (region == null || textViewer == null) {
			return null;
		}
		IDocument document = textViewer.getDocument();

		int offset = region.getOffset();

		String partition = document.getDocumentPartitioner().getContentType(
				offset);
		if (SqlPartitionScanner.SQL_COMMENT.equals(partition)) {
			int end = DocumentUtil.skip(document, offset,
					Constants.commentEndOrWhitespace);
			String maybeEL = DocumentUtil.backto(document, end,
					Constants.commentOrWhitespace);

		}
		return null;
	}

}
