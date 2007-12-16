package twowaysqleditor;

import static twowaysqleditor.rules.SqlPartitionScanner.*;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

import twowaysqleditor.rules.SqlPartitionScanner;

public class SqlDocumentProvider extends FileDocumentProvider {
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument result = super.createDocument(element);
		if (result != null) {
			IDocumentPartitioner p = new FastPartitioner(
					new SqlPartitionScanner(), new String[] { SQL_IF, SQL_ELSE,
							SQL_BEGIN, SQL_END, SQL_BIND });
			p.connect(result);
			result.setDocumentPartitioner(p);
		}
		return result;
	}
}
