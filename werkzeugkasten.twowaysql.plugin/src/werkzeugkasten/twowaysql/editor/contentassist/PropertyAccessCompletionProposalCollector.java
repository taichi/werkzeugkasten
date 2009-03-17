package werkzeugkasten.twowaysql.editor.contentassist;

import java.util.Arrays;

import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;

public class PropertyAccessCompletionProposalCollector extends
		ELCompletionProposalCollector {

	protected static final char[] JAVA_LANG_STRING = "java.lang.String"
			.toCharArray();

	protected static final String LENGTH = "length()";

	public PropertyAccessCompletionProposalCollector(IJavaProject project,
			int baseOffset) {
		super(project, baseOffset);
	}

	@Override
	protected boolean isFiltered(CompletionProposal proposal) {
		if (CompletionProposal.METHOD_REF == proposal.getKind()) {
			char[] comp = proposal.getCompletion();
			if (comp.length < 3 || comp[0] != 'g' || comp[1] != 'e'
					|| comp[2] != 't'
					|| 0 < proposal.findParameterNames(null).length) {
				char[] decl = getDeclaringType(proposal);
				if (Arrays.equals(JAVA_LANG_STRING, decl)
						&& String.valueOf(proposal.getCompletion()).endsWith(
								LENGTH)) {
					return false;
				}

				return true;
			}
		}
		return super.isFiltered(proposal);
	}

	@Override
	protected IJavaCompletionProposal createJavaCompletionProposal(
			CompletionProposal proposal) {
		if (proposal.getKind() == CompletionProposal.METHOD_REF) {
			CompletionProposal newone = CompletionProposal.create(
					CompletionProposal.FIELD_REF, proposal
							.getCompletionLocation());

			char[] old = proposal.getCompletion();
			String compStr = String.valueOf(old);
			int copyBegin = compStr.endsWith(LENGTH) ? 0 : 3;
			char[] comp = new char[compStr.indexOf('(') - copyBegin];
			System.arraycopy(old, copyBegin, comp, 0, comp.length);
			comp[0] = Character.toLowerCase(comp[0]);
			newone.setCompletion(comp);

			old = proposal.getName();
			char[] name = new char[old.length - copyBegin];
			System.arraycopy(old, copyBegin, name, 0, name.length);
			name[0] = Character.toLowerCase(name[0]);
			newone.setName(name);

			old = Signature.getSignatureSimpleName(proposal.getSignature());
			int index = String.valueOf(old).indexOf('(');
			char[] sig = new char[0 < index ? index : old.length];
			System.arraycopy(old, 0, sig, 0, sig.length);
			newone.setSignature(Signature.createCharArrayTypeSignature(sig,
					true));

			newone.setAdditionalFlags(proposal.getAdditionalFlags());
			newone.setDeclarationKey(proposal.getDeclarationKey());
			newone.setDeclarationSignature(proposal.getDeclarationSignature());
			newone.setFlags(proposal.getFlags());
			newone.setKey(proposal.getKey());
			newone.setRelevance(proposal.getRelevance());
			newone.setRequiredProposals(proposal.getRequiredProposals());
			newone.setTokenRange(proposal.getTokenStart(), proposal
					.getTokenEnd());
			newone.setReplaceRange(proposal.getReplaceStart(), proposal
					.getReplaceEnd());
			proposal = newone;
		}
		return super.createJavaCompletionProposal(proposal);
	}

}
