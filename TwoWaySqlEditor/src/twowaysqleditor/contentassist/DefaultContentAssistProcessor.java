package twowaysqleditor.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class DefaultContentAssistProcessor implements IContentAssistProcessor {

	protected static final String[] PROPOSALS = new String[] { "/*IF ",
			"-- ELSE ", "/*BEGIN*/\r\n/*END*/", "/*END*/" };
	protected static final int[] PROPOSAL_CURSOR = new int[] { 5, 8, 9, 7 };

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
		for (int i = 0; i < PROPOSALS.length; i++) {
			result.add(new CompletionProposal(PROPOSALS[i], offset, 0,
					PROPOSAL_CURSOR[i]));
		}
		return result.toArray(new ICompletionProposal[result.size()]);
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
