package werkzeugkasten.twowaysql.editor.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class TextContentAssistProcessor implements IContentAssistProcessor {
	protected String errorMessage;

	protected static final String[] KEYWORDS = { "/*IF  */", "/*ELSEIF  */",
			"--ELSE", "/*BEGIN*/", "/*END*/" };
	protected static final int[] CURSOR_POS = { 5, 9, KEYWORDS[2].length(),
			KEYWORDS[3].length(), KEYWORDS[4].length() };

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		IDocument doc = viewer.getDocument();
		String backto = backto(offset, doc);
		// System.out.printf("backto [%s] %n", backto);
		for (int i = 0, length = KEYWORDS.length; i < length; i++) {
			String s = KEYWORDS[i];
			if (s.startsWith(backto)) {
				s += doc.getLegalLineDelimiters()[0];
				proposals.add(new CompletionProposal(s, offset
						- backto.length(), backto.length(), CURSOR_POS[i]));
			} else {
				s += doc.getLegalLineDelimiters()[0];
				proposals.add(new CompletionProposal(s, offset, 0,
						CURSOR_POS[i]));
			}

		}
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	private String backto(int offset, IDocument doc) {
		try {
			boolean skipped = false;
			int index = offset;
			while (-1 < index) {
				char c = doc.getChar(index);
				if (skipped == false) {
					if (Character.isWhitespace(c) == false) {
						skipped = true;
					}
				} else {
					if (Character.isWhitespace(c)) {
						index++;
						break;
					}
				}
				index--;
			}
			return doc.get(index, offset - index);
		} catch (BadLocationException e) {
			return "";
		}
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	@Override
	public String getErrorMessage() {
		return this.errorMessage;
	}

}
