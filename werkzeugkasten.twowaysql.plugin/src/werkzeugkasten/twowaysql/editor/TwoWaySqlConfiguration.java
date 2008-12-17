package werkzeugkasten.twowaysql.editor;

import org.eclipse.jface.text.source.SourceViewerConfiguration;

import werkzeugkasten.twowaysql.editor.conf.ColorManager;

public class TwoWaySqlConfiguration extends SourceViewerConfiguration {

	protected ColorManager colorManager;

	public TwoWaySqlConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
}
