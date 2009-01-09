package werkzeugkasten.twowaysql.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.editor.scanner.TwoWaySqlPartitionScanner;
import werkzeugkasten.twowaysql.util.DocumentUtil;

public class TwoWaySqlDocumentSetupParticipant implements
		IDocumentSetupParticipant {

	@Override
	public void setup(IDocument document) {
		IDocumentPartitioner dp = createDocumentPartitioner();
		DocumentUtil.setDocumentPartitioner(document,
				Constants.PARTITION_TYPE_TWOWAYSQL, dp);
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
