package werkzeugkasten.twowaysql.util;

import java.util.Iterator;

import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelExtension;

import werkzeugkasten.common.runtime.AdaptableUtil;

public class AnnotationUtil {

	public static void removeAllAnnotations(IAnnotationModel model) {
		if (model instanceof IAnnotationModelExtension) {
			IAnnotationModelExtension ame = (IAnnotationModelExtension) model;
			ame.removeAllAnnotations();
		} else {
			for (Iterator<?> i = model.getAnnotationIterator(); i.hasNext();) {
				model.removeAnnotation(AdaptableUtil.to(i.next(),
						Annotation.class));
			}
		}
	}
}
