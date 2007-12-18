package twowaysqleditor.contentassist;

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

import twowaysqleditor.Activator;
import twowaysqleditor.util.DocumentUtil;

public class DefaultContentAssistProcessor implements IContentAssistProcessor {

	protected static final String[] PROPOSALS = new String[] { "/* */",
			"/*IF ", "-- ELSE ", "/*BEGIN*/\r\n/*END*/", "/*END*/" };
	protected static final int[] PROPOSAL_CURSOR = new int[] { 2, 5, 8, 9, 7 };

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		try {
			int proposalsIndex = 0;

			List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
			IDocument document = viewer.getDocument();
			if (1 < offset && document.getChar(offset - 1) == '/'
					&& document.getChar(offset - 2) != '*') {
				result.add(new CompletionProposal("/* */", offset - 1, 1, 2));
				proposalsIndex++;
			}

			String backto = DocumentUtil.backto(document, offset,
					DocumentUtil.whitespaceOrLineDelims);

			for (int i = proposalsIndex; i < PROPOSALS.length; i++) {
				String s = PROPOSALS[i];
				if (s.startsWith(backto)) {
					result.add(new CompletionProposal(PROPOSALS[i], offset, 0,
							PROPOSAL_CURSOR[i]));
				}
			}
			return result.toArray(new ICompletionProposal[result.size()]);
		} catch (BadLocationException e) {
			Activator.log(e);
			return null;
		}
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	public String getErrorMessage() {
		return null;
	}

}
