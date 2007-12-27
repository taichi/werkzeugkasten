package twowaysqleditor;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.RGB;

import twowaysqleditor.util.DocumentUtil;

public class Constants {

	public static final String ID_PLUGIN = "TwoWaySqlEditor";
	/* ---------------------------------------------- */
	public static final RGB BLACK = new RGB(0, 0, 0);

	public static final RGB WHITE = new RGB(255, 255, 255);

	public static final RGB KEYWORD = new RGB(140, 0, 0);

	public static final RGB GREEN = new RGB(0, 140, 0);

	/* ---------------------------------------------- */
	public static final String DEFID_FORMAT = "twowaysqleditor.format";

	/* ---------------------------------------------- */
	public static final ICompletionProposal[] EMPTY_PROPOSAL = new ICompletionProposal[0];

	/* ---------------------------------------------- */
	public static final char[] DOT = new char[] { '.' };

	public static final DocumentUtil.Detector commentOrWhitespace = new DocumentUtil.Detector() {
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			int ch = d.getChar(index);
			return Character.isWhitespace(ch)
					|| (1 < index && ch == '*' && d.getChar(index - 1) == '/');
		}
	};

	public static final DocumentUtil.Detector commentEndOrWhitespace = new DocumentUtil.Detector() {
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			int ch = d.getChar(index);
			return Character.isWhitespace(ch)
					|| (++index < d.getLength() && ch == '*' && d
							.getChar(index) == '/');
		}
	};

	public static final DocumentUtil.Detector ignoreCommentEndOrWhitespace = new DocumentUtil.Detector() {
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			return commentEndOrWhitespace.detect(d, index) == false;
		}
	};

}
