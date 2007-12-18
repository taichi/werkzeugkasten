package twowaysqleditor.util;

import java.util.Arrays;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class DocumentUtil {

	public static Detector whitespace = new Detector() {
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			return Character.isWhitespace(d.getChar(index));
		}
	};

	public static final int[] LineDelimitors = { '\r', '\n' };
	public static Detector whitespaceOrLineDelims = new Detector() {
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			int ch = d.getChar(index);
			return Character.isWhitespace(ch)
					|| -1 < Arrays.binarySearch(LineDelimitors, ch);
		}
	};

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
