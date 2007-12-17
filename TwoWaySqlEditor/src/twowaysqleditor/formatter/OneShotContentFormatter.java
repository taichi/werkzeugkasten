package twowaysqleditor.formatter;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;

import blanco.commons.sql.format.BlancoSqlFormatter;
import blanco.commons.sql.format.BlancoSqlRule;

public class OneShotContentFormatter implements IContentFormatter {
	protected BlancoSqlFormatter formatter = new BlancoSqlFormatter(
			new BlancoSqlRule());

	public void format(IDocument document, IRegion region) {
		try {
			String sql = document.get();
			String text = formatter.format(sql);
			document.set(text);
		} catch (Exception e) {
			e.printStackTrace(); // TODO logging exceptions
		}
	}

	public IFormattingStrategy getFormattingStrategy(String contentType) {
		return null;
	}

}
