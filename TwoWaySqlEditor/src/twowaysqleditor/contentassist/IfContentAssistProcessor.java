package twowaysqleditor.contentassist;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.text.java.CompletionProposalCollector;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import twowaysqleditor.Activator;
import twowaysqleditor.Constants;
import twowaysqleditor.EditorContext;
import twowaysqleditor.util.DocumentUtil;

public class IfContentAssistProcessor implements IContentAssistProcessor {

	protected static final char[] JAVA_LANG_OBJECT = "java.lang.Object"
			.toCharArray();

	protected EditorContext context;

	public IfContentAssistProcessor(EditorContext context) {
		this.context = context;
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		try {
			IDocument document = viewer.getDocument();
			String backto = DocumentUtil.backto(document, offset - 1,
					DocumentUtil.whitespace);

			IFile sql = context.getSqlFile();
			if (sql == null) {
				return Constants.EMPTY_PROPOSAL;
			}
			IJavaProject project = JavaCore.create(sql.getProject());
			if (project == null) {
				return Constants.EMPTY_PROPOSAL;
			}

			DummyCompilationUnit dummy = DummyCompilationUnit.create(backto,
					context.getMethod());
			if (dummy == null) {
				return Constants.EMPTY_PROPOSAL;
			}

			CompletionProposalCollector collector = new ELCompletionProposalCollector(
					project, dummy.getDummyClassName(), -dummy.getBefore()
							+ offset);
			dummy.codeComplete(collector);

			return collector.getJavaCompletionProposals();
		} catch (CoreException e) {
			Activator.log(e);
		}
		return Constants.EMPTY_PROPOSAL;
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return Constants.DOT;
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return Constants.DOT;
	}

	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	public String getErrorMessage() {
		return null;
	}

}
