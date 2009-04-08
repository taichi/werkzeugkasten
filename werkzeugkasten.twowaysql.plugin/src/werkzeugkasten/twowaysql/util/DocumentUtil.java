package werkzeugkasten.twowaysql.util;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;

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
			String partitionType, int offset) throws BadLocationException,
			BadPartitioningException {
		ITypedRegion partition = null;
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 de3 = (IDocumentExtension3) document;
			partition = de3.getPartition(partitionType, offset, true);
		} else {
			partition = document.getPartition(offset);
		}
		return partition;
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
			while (index < doc.getLength() && d.detect(doc, index)) {
				index++;
			}
			return index;
		} catch (BadLocationException e) {
			return offset;
		}
	}

	public static String backto(IDocument doc, int offset, Detector d) {
		try {
			int index = offset;
			for (; -1 < index; index--) {
				if (d.detect(doc, index) == false) {
					break;
				}
			}
			return doc.get(index + 1, offset - index);
		} catch (BadLocationException e) {
			return "";
		}
	}

}
