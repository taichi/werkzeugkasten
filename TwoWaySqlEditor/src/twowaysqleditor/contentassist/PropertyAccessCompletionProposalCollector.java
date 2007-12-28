package twowaysqleditor.contentassist;

import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;

public class PropertyAccessCompletionProposalCollector extends
		ELCompletionProposalCollector {

	public PropertyAccessCompletionProposalCollector(IJavaProject project,
			String dummytype, int baseOffset) {
		super(project, dummytype, baseOffset);
	}

	@Override
	protected boolean isFiltered(CompletionProposal proposal) {
		if (CompletionProposal.METHOD_REF == proposal.getKind()) {
			char[] comp = proposal.getCompletion();
			if (comp.length < 3 || comp[0] != 'g' || comp[1] != 'e'
					|| comp[2] != 't'
					|| 0 < proposal.findParameterNames(null).length) {
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
			newone.setAdditionalFlags(proposal.getAdditionalFlags());
			char[] old = proposal.getCompletion();
			char[] comp = new char[String.valueOf(old).indexOf('(') - 3];
			System.arraycopy(old, 3, comp, 0, comp.length);
			comp[0] = Character.toLowerCase(comp[0]);
			newone.setCompletion(comp);
			newone.setDeclarationKey(proposal.getDeclarationKey());
			newone.setDeclarationSignature(proposal.getDeclarationSignature());
			newone.setFlags(proposal.getFlags());
			newone.setKey(proposal.getKey());
			old = proposal.getName();
			char[] name = new char[old.length - 3];
			System.arraycopy(old, 3, name, 0, name.length);
			name[0] = Character.toLowerCase(name[0]);
			newone.setName(name);
			newone.setRelevance(proposal.getRelevance());
			newone.setRequiredProposals(proposal.getRequiredProposals());
			old = Signature.getSignatureSimpleName(proposal.getSignature());
			int index = String.valueOf(old).indexOf('(');
			char[] sig = new char[0 < index ? index : old.length];
			System.arraycopy(old, 0, sig, 0, sig.length);
			newone.setSignature(Signature.createCharArrayTypeSignature(sig,
					true));
			newone.setTokenRange(proposal.getTokenStart(), proposal
					.getTokenEnd());
			newone.setReplaceRange(proposal.getReplaceStart(), proposal
					.getReplaceEnd());
			proposal = newone;
		}
		return super.createJavaCompletionProposal(proposal);
	}

}
