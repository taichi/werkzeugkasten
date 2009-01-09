package werkzeugkasten.twowaysql.util;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;

import werkzeugkasten.twowaysql.Constants;

public class DocumentUtil {

	public static void setDocumentPartitioner(IDocument document,
			String partitionType, IDocumentPartitioner dp) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 de3 = (IDocumentExtension3) document;
			de3.setDocumentPartitioner(Constants.PARTITION_TYPE_TWOWAYSQL, dp);
		} else {
			document.setDocumentPartitioner(dp);
		}
	}

	public static ITypedRegion getPartition(IDocument document,
			String partitionType, int offset) throws BadLocationException,
			BadPartitioningException {
		ITypedRegion partition = null;
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 de3 = (IDocumentExtension3) document;
			partition = de3.getPartition(partitionType, offset, true);
		} else {
			partition = document.getPartition(offset);
		}
		return partition;
	}
}
