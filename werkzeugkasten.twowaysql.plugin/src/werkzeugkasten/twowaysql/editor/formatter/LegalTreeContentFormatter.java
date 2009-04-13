package werkzeugkasten.twowaysql.editor.formatter;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;

public class LegalTreeContentFormatter implements IContentFormatter {

	@Override
	public void format(IDocument document, IRegion region) {
	}

	@Override
	public IFormattingStrategy getFormattingStrategy(String contentType) {
		return null;
	}

}
