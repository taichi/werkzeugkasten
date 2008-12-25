package werkzeugkasten.twowaysql.editor.conf;

import java.util.EnumMap;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.RGB;

import werkzeugkasten.common.util.Disposable;
import werkzeugkasten.common.util.Initializable;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants.COLORING;

public class ColorDefineFactory implements IPropertyChangeListener,
		Initializable, Disposable {

	protected Map<COLORING, IToken> configs = new EnumMap<COLORING, IToken>(
			COLORING.class);

	public ColorDefineFactory() {
		Activator.getGlobalPreference().addPropertyChangeListener(this);
	}

	@Override
	public void initialize() {
		IPreferenceStore store = Activator.getGlobalPreference();
		for (COLORING c : COLORING.values()) {
			configs.put(c, createToken(c.getPrefColorKey(), store.getInt(c
					.getPrefStyleKey())));

		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getProperty();
		for (COLORING c : COLORING.values()) {
			if (c.name().equals(property)) {
				Object newone = event.getNewValue();
				if (newone instanceof ColorConfig) {
					ColorConfig cc = (ColorConfig) newone;
					configs
							.put(
									c,
									createToken(c.getPrefColorKey(), cc.style()));
				}
			}
		}
	}

	protected Token createToken(String key, int style) {
		return new Token(createTxtAttr(key, style));
	}

	protected TextAttribute createTxtAttr(String key, int style) {
		ColorManager cm = Activator.getColorManager();
		IPreferenceStore store = Activator.getGlobalPreference();
		RGB rgb = PreferenceConverter.getColor(store, key);
		return new TextAttribute(cm.getColor(rgb), null, style);
	}

	public IToken getToken(COLORING type) {
		return configs.get(type);
	}

	@Override
	public void dispose() {
		Activator.getGlobalPreference().removePropertyChangeListener(this);
	}
}
