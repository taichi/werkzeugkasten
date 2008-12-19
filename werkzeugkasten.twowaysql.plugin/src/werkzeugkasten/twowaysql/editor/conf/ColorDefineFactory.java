package werkzeugkasten.twowaysql.editor.conf;

import java.util.EnumMap;
import java.util.Map;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import werkzeugkasten.common.util.Disposable;
import werkzeugkasten.common.util.Initializable;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants.COLORING;

public class ColorDefineFactory implements IPropertyChangeListener,
		Initializable, Disposable {

	protected Map<COLORING, IToken> configs = new EnumMap<COLORING, IToken>(
			COLORING.class);
	protected ColorManager cm;

	public ColorDefineFactory() {
		this.cm = ColorManager.getDefault();
		Activator.getGlobalPreference().addPropertyChangeListener(this);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		// setup from configuration.
		// any default value is already set.
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getProperty();
		for (COLORING c : COLORING.values()) {
			if (c.name().equals(property)) {
				Object newone = event.getNewValue();
				if (newone instanceof ColorConfig) {
					ColorConfig cc = (ColorConfig) newone;
				}
			}
		}
	}

	protected TextAttribute createTxtAttr(ColorConfig cc) {
		return null;
	}

	public IToken getToken(COLORING type) {
		return null;
	}

	@Override
	public void dispose() {
		Activator.getGlobalPreference().removePropertyChangeListener(this);
		this.cm.dispose();
	}
}
