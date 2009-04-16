package werkzeugkasten.twowaysql.util;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;

public class DocumentUtil {

	public static void setDocumentPartitioner(IDocument document,
			String partitionType, IDocumentPartitioner dp) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 de3 = (IDocumentExtension3) document;
			de3.setDocumentPartitioner(partitionType, dp);
		} else {
			document.setDocumentPartitioner(dp);
		}
	}

	public static ITypedRegion getPartition(IDocument document,
			String partitionType, int offset) throws BadLocationException {
		return TextUtilities
				.getPartition(document, partitionType, offset, true);
	}

	public static ITypedRegion[] computePartitioning(IDocument document,
			String partitionType) throws BadLocationException {
		return TextUtilities.computePartitioning(document, partitionType, 0,
				document.getLength(), false);
	}

	public interface Detector {
		boolean detect(IDocument d, int index) throws BadLocationException;
	}

	public static Detector whitespace = new Detector() {
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			return Character.isWhitespace(d.getChar(index));
		}
	};

	public static Detector ignoreWhitespace = new Detector() {
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			return whitespace.detect(d, index) == false;
		}
	};

	public static int skip(IDocument doc, int offset, Detector d) {
		try {
			int index = offset;
			while (index < doc.getLength() && d.detect(doc, ++index))
				;
			return index;
		} catch (BadLocationException e) {
			return offset;
		}
	}

	public static int backto(IDocument doc, int offset, Detector d) {
		try {
			int index = offset;
			while (-1 < index && d.detect(doc, --index))
				;
			return index + 1;
		} catch (BadLocationException e) {
			return offset;
		}
	}

}
