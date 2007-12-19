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

import twowaysqleditor.Constants;
import twowaysqleditor.EditorContext;
import twowaysqleditor.util.DocumentUtil;

public class CommentContentAssistProcessor implements IContentAssistProcessor {

	protected EditorContext context;

	protected DefaultContentAssistProcessor inner;

	public CommentContentAssistProcessor(EditorContext context) {
		this.context = context;
		this.inner = new DefaultContentAssistProcessor(context);
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
		IDocument document = viewer.getDocument();
		String backto = DocumentUtil.backto(document, offset - 1,
				Constants.commentOrWhitespace);

		for (String s : context.getArgNames()) {
			if (s.startsWith(backto) && backto.endsWith(s) == false) {
				result.add(new CompletionProposal(s, offset - backto.length(),
						backto.length(), s.length()));
			}
		}
		result.addAll(Arrays.asList(inner.computeCompletionProposals(viewer,
				offset)));
		return result.toArray(new ICompletionProposal[result.size()]);
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return Constants.DOT;
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
