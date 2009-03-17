package werkzeugkasten.twowaysql.editor.contentassist;

import java.util.Arrays;

import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.ui.text.java.CompletionProposalCollector;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;

public class ELCompletionProposalCollector extends CompletionProposalCollector {
	protected static final char[] JAVA_LANG_OBJECT = "java.lang.Object"
			.toCharArray();

	protected int baseOffset;

	public ELCompletionProposalCollector(IJavaProject project, int baseOffset) {
		super(project);
		setIgnored(CompletionProposal.TYPE_REF, true);
		setIgnored(CompletionProposal.PACKAGE_REF, true);
		setIgnored(CompletionProposal.KEYWORD, true);
		this.baseOffset = baseOffset;
	}

	@Override
	protected boolean isFiltered(CompletionProposal proposal) {
		int kind = proposal.getKind();
		if (kind == CompletionProposal.LOCAL_VARIABLE_REF
				|| kind == CompletionProposal.METHOD_REF) {
			char[] declaringType = getDeclaringType(proposal);
			if (Arrays.equals(JAVA_LANG_OBJECT, declaringType)) {
				return true;
			}
			return false;
		}
		return super.isFiltered(proposal);
	}

	@Override
	protected IJavaCompletionProposal createJavaCompletionProposal(
			CompletionProposal proposal) {
		int startIndex = proposal.getReplaceStart() + baseOffset;
		int endIndex = proposal.getReplaceEnd() + baseOffset;
		proposal.setReplaceRange(startIndex, endIndex);
		return super.createJavaCompletionProposal(proposal);
	}

}
