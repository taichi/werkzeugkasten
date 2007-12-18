package twowaysqleditor.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.ui.text.java.AbstractJavaCompletionProposal;
import org.eclipse.jdt.ui.text.java.CompletionProposalCollector;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import twowaysqleditor.Activator;
import twowaysqleditor.EditorContext;
import twowaysqleditor.util.DocumentUtil;

public class IfContentAssistProcessor implements IContentAssistProcessor {

	protected EditorContext context;

	public IfContentAssistProcessor(EditorContext context) {
		this.context = context;
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		try {
			IFile sql = context.getSqlFile();
			if (sql == null) {
				return null;
			}
			IJavaProject project = JavaCore.create(sql.getProject());
			if (project == null) {
				return null;
			}

			IDocument document = viewer.getDocument();
			String backto = DocumentUtil.backto(document, offset - 1,
					DocumentUtil.whitespace);
			DummyCompilationUnit dummy = DummyCompilationUnit.create(backto,
					context.getMethod());
			if (dummy == null) {
				return null;
			}
			CompletionProposalCollector collector = new CompletionProposalCollector(
					project);
			collector.setIgnored(CompletionProposal.TYPE_REF, true);
			collector.setIgnored(CompletionProposal.PACKAGE_REF, true);
			collector.setIgnored(CompletionProposal.KEYWORD, true);

			int before = dummy.getBefore();
			ICompilationUnit unit = dummy.getUnit();
			unit.codeComplete(before, collector);
			IJavaCompletionProposal[] proposals = collector
					.getJavaCompletionProposals();
			List<IJavaCompletionProposal> list = new ArrayList<IJavaCompletionProposal>();
			String typename = DummyCompilationUnit.toDummyType(context
					.getMethod());
			for (IJavaCompletionProposal p : proposals) {
				if (p instanceof AbstractJavaCompletionProposal) {
					AbstractJavaCompletionProposal ajcp = (AbstractJavaCompletionProposal) p;
					String display = p.getDisplayString();
					if (display.endsWith(typename) == false
							&& display.endsWith("- Object") == false) {
						ajcp.setReplacementOffset(ajcp.getReplacementOffset()
								- before + offset);
						list.add(ajcp);
					}
				}
			}
			return list.toArray(new IJavaCompletionProposal[list.size()]);
		} catch (CoreException e) {
			Activator.log(e);
		}
		return null;
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
