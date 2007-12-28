package twowaysqleditor.contentassist;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
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
import twowaysqleditor.util.TypeUtil;

public class CommentContentAssistProcessor implements IContentAssistProcessor {

	protected EditorContext context;

	protected DefaultContentAssistProcessor inner;

	public CommentContentAssistProcessor(EditorContext context) {
		this.context = context;
		this.inner = new DefaultContentAssistProcessor(context);
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		try {
			IDocument document = viewer.getDocument();
			String backto = DocumentUtil.backto(document, offset - 1,
					Constants.commentOrWhitespace);

			IFile sql = context.getSqlFile();
			if (sql == null) {
				return Constants.EMPTY_PROPOSAL;
			}
			IJavaProject project = JavaCore.create(sql.getProject());
			if (project == null) {
				return Constants.EMPTY_PROPOSAL;
			}

			String prefix = createPrefix(backto);
			System.out.println(prefix);
			DummyCompilationUnit dummy = DummyCompilationUnit.create(prefix,
					context.getMethod());
			if (dummy == null) {
				return Constants.EMPTY_PROPOSAL;
			}

			CompletionProposalCollector collector = new PropertyAccessCompletionProposalCollector(
					project, dummy.getDummyClassName(), -dummy.getBefore()
							+ offset);
			dummy.codeComplete(collector);

			return collector.getJavaCompletionProposals();
		} catch (CoreException e) {
			Activator.log(e);
		}
		return Constants.EMPTY_PROPOSAL;

	}

	protected String createPrefix(String backto) throws CoreException {
		String[] split = backto.split("\\.");
		StringBuilder stb = new StringBuilder();

		IMethod method = context.getMethod();
		IJavaProject project = method.getJavaProject();
		IType type = method.getDeclaringType();
		String[] args = method.getParameterNames();
		for (int i = 0; i < args.length; i++) {
			if (split[0].equalsIgnoreCase(args[i])) {
				String s = method.getParameterTypes()[i];
				type = project.findType(TypeUtil.getResolvedTypeName(s, type));
				break;
			}
		}

		stb.append(split[0]);
		for (int i = 1; i < split.length; i++) {
			IField f = type.getField(split[i]);
			if (f != null && f.exists()) {
				stb.append('.');
				stb.append(split[i]);
			} else {
				StringBuilder sb = new StringBuilder(split[i]);
				char ch = Character.toUpperCase(sb.charAt(0));
				sb.setCharAt(0, ch);
				sb.insert(0, "get");
				IMethod m = TypeUtil.getAccesserMethod(type, sb.toString());
				if (m == null || m.exists() == false) {
					m = TypeUtil.getAccesserMethod(type, split[i]);
				}
				if (m != null && m.exists() && m.getNumberOfParameters() < 1) {
					stb.append('.');
					stb.append(m.getElementName());
					stb.append("()");
					type = project.findType(TypeUtil.getResolvedTypeName(m
							.getReturnType(), type));
				}
			}
		}
		if (0 < backto.length() && backto.charAt(backto.length() - 1) == '.') {
			stb.append('.');
		}
		return stb.toString();
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
