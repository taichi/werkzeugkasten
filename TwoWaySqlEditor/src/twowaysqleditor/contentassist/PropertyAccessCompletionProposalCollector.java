package twowaysqleditor.contentassist;

import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.IJavaProject;
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
					|| comp[2] != 't') {
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
			char[] comp = new char[old.length - 3];
			System.arraycopy(old, 3, comp, 0, comp.length);
			comp[0] = Character.toLowerCase(comp[0]);

			System.out.println(String.valueOf(comp));

			newone.setCompletion(comp);
			newone.setDeclarationKey(proposal.getDeclarationKey());
			newone.setDeclarationSignature(proposal.getDeclarationSignature());
			newone.setFlags(proposal.getFlags());
			newone.setKey(proposal.getKey());
			newone.setName(proposal.getName());
			newone.setRelevance(proposal.getRelevance());
			newone.setRequiredProposals(proposal.getRequiredProposals());
			newone.setSignature(proposal.getSignature());
			newone.setTokenRange(proposal.getTokenStart(), proposal
					.getTokenEnd());
			int startIndex = proposal.getReplaceStart() + baseOffset - 3;
			newone.setReplaceRange(startIndex, proposal.getReplaceEnd() - 3);
			proposal = newone;
		}
		return super.createJavaCompletionProposal(proposal);
	}

}
