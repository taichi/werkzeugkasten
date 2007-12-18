package twowaysqleditor.util;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class DocumentUtil {

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
				if (d.detect(doc, index)) {
					break;
				}
			}
			return doc.get(index + 1, offset - index);
		} catch (BadLocationException e) {
			return "";
		}
	}

	public interface Detector {
		boolean detect(IDocument d, int index) throws BadLocationException;
	}
}
