package werkzeugkasten.twowaysql.editor.formatter;

import java.util.Iterator;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;

import werkzeugkasten.twowaysql.editor.QueryProblemAnnotation;

public class StateBasedSelectableContentFormatter implements IContentFormatter {

	protected ISourceViewer viewer;

	protected IllegalTreeContentFormatter illegal = new IllegalTreeContentFormatter();
	protected LegalTreeContentFormatter legal = new LegalTreeContentFormatter();

	public StateBasedSelectableContentFormatter(ISourceViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void format(IDocument document, IRegion region) {
		if (hasProblem()) {
			illegal.format(document, region);
		} else {
			legal.format(document, region);
		}
	}

	protected boolean hasProblem() {
		IAnnotationModel model = this.viewer.getAnnotationModel();
		for (Iterator<?> i = model.getAnnotationIterator(); i.hasNext();) {
			if (i.next() instanceof QueryProblemAnnotation) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IFormattingStrategy getFormattingStrategy(String contentType) {
		return null;
	}

}
