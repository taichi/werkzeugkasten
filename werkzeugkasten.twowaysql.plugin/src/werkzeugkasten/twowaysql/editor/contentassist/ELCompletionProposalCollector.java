package werkzeugkasten.twowaysql.editor.contentassist;

import java.util.Arrays;

import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.Signature;
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

		if (isPropertyAccess(proposal)) {
			proposal = convertToPropertyAccess(proposal);
		}
		return super.createJavaCompletionProposal(proposal);
	}

	protected boolean isPropertyAccess(CompletionProposal proposal) {
		if (proposal.getKind() == CompletionProposal.METHOD_REF) {
			char[] comp = proposal.getCompletion();
			if (comp != null && 2 < comp.length && comp[0] == 'g'
					&& comp[1] == 'e' && comp[2] == 't') {
				char[][] names = proposal.findParameterNames(null);
				if (names == null || names.length < 1) {
					// TODO java.lang.String#length() に対する特別扱いするかを考慮する事。
					return true;
				}
			}
		}
		return false;
	}

	protected CompletionProposal convertToPropertyAccess(
			CompletionProposal proposal) {
		CompletionProposal result = CompletionProposal.create(
				CompletionProposal.FIELD_REF, proposal.getCompletionLocation());

		char[] old = proposal.getCompletion();
		String compStr = String.valueOf(old);
		int copyBegin = 3;
		char[] comp = new char[compStr.indexOf('(') - 3];
		System.arraycopy(old, copyBegin, comp, 0, comp.length);
		comp[0] = Character.toLowerCase(comp[0]);
		result.setCompletion(comp);

		old = proposal.getName();
		char[] name = new char[old.length - copyBegin];
		System.arraycopy(old, copyBegin, name, 0, name.length);
		name[0] = Character.toLowerCase(name[0]);
		result.setName(name);

		old = Signature.getSignatureSimpleName(proposal.getSignature());
		int index = String.valueOf(old).indexOf('(');
		char[] sig = new char[0 < index ? index : old.length];
		System.arraycopy(old, 0, sig, 0, sig.length);
		result.setSignature(Signature.createCharArrayTypeSignature(sig, true));

		result.setAdditionalFlags(proposal.getAdditionalFlags());
		result.setDeclarationKey(proposal.getDeclarationKey());
		result.setDeclarationSignature(proposal.getDeclarationSignature());
		result.setFlags(proposal.getFlags());
		result.setKey(proposal.getKey());
		result.setRelevance(proposal.getRelevance());
		result.setRequiredProposals(proposal.getRequiredProposals());
		result.setTokenRange(proposal.getTokenStart(), proposal.getTokenEnd());
		result.setReplaceRange(proposal.getReplaceStart(), proposal
				.getReplaceEnd());
		return result;
	}
}
