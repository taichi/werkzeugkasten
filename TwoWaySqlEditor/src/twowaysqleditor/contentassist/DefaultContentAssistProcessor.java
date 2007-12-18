package twowaysqleditor.contentassist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import twowaysqleditor.EditorContext;
import twowaysqleditor.util.DocumentUtil;

public class DefaultContentAssistProcessor implements IContentAssistProcessor {

	protected static final String[] PROPOSALS = new String[] { "/* ", "/*IF ",
			"-- ELSE ", "/*BEGIN*/", "/*END*/", "*/" };
	protected static final int[] PROPOSAL_CURSOR = new int[] { 2, 5, 8, 9, 7, 2 };

	protected EditorContext context;

	protected IfContentAssistProcessor innerProcesser;

	public DefaultContentAssistProcessor(EditorContext context) {
		this.context = context;
		this.innerProcesser = new IfContentAssistProcessor(context);
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
		IDocument document = viewer.getDocument();
		String backto = DocumentUtil.backto(document, offset - 1,
				DocumentUtil.whitespace);
		for (int i = 0; i < PROPOSALS.length; i++) {
			String s = PROPOSALS[i];
			if (s.startsWith(backto) && s.equals(backto) == false) {
				result.add(new CompletionProposal(s, offset - backto.length(),
						backto.length(), PROPOSAL_CURSOR[i]));
			}
		}
		result.addAll(Arrays.asList(innerProcesser.computeCompletionProposals(
				viewer, offset)));
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
