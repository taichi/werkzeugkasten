package werkzeugkasten.twowaysql.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

import werkzeugkasten.twowaysql.Constants;

public class TwoWaySqlDocumentSetupParticipant implements
		IDocumentSetupParticipant {

	@Override
	public void setup(IDocument document) {
		IDocumentPartitioner dp = createDocumentPartitioner();
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 de3 = (IDocumentExtension3) document;
			de3.setDocumentPartitioner(Constants.PARTITION_TYPE_TWOWAYSQL, dp);
		} else {
			document.setDocumentPartitioner(dp);
		}
		dp.connect(document);
	}

	protected IDocumentPartitioner createDocumentPartitioner() {
		return new FastPartitioner(createPartitionScanner(),
				Constants.LEGAL_CONTENT_TYPES);
	}

	protected IPartitionTokenScanner createPartitionScanner() {
		return new TwoWaySqlPartitionScanner();
	}

}
