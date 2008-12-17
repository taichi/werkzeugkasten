package werkzeugkasten.twowaysql.editor.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import werkzeugkasten.common.util.LazyLoadingReference;
import werkzeugkasten.twowaysql.Activator;

public class ColorManager implements IPropertyChangeListener {

	static LazyLoadingReference<ColorManager> instance = new LazyLoadingReference<ColorManager>(
			new LazyLoadingReference.Factory<ColorManager>() {
				@Override
				public ColorManager create() {
					ColorManager cm = new ColorManager();
					IPreferenceStore pref = Activator.getGlobalPreference();
					cm.initialize(pref);
					return cm;
				}
			});

	protected Map<String, Color> colors = new HashMap<String, Color>();
	protected AtomicInteger counter = new AtomicInteger(0);

	protected void increment() {
		int v;
		do {
			v = counter.get();
		} while (counter.compareAndSet(v, v + 1) == false);
	}

	public static ColorManager getDefault() {
		ColorManager cm = instance.get();
		cm.increment();
		return cm;
	}

	public void initialize(IPreferenceStore pref) {

		pref.addPropertyChangeListener(this);
	}

	protected void putColor(String key, RGB rgb) {
		Color c = colors.get(key);
		if (c != null) {
			if (c.getRGB().equals(rgb)) {
				return;
			}
			c.dispose();
		}
		colors.put(key, new Color(Display.getCurrent(), rgb));
	}

	public Color getColor(String key) {
		Color c = colors.get(key);
		if (c == null) {
			c = Display.getCurrent().getSystemColor(SWT.COLOR_LIST_FOREGROUND);
		}
		return c;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		Object value = event.getNewValue();
		if (value instanceof RGB) {
			RGB rgb = (RGB) value;
			putColor(event.getProperty(), rgb);
		} else if (value != null) {
			putColor(event.getProperty(), StringConverter.asRGB(value
					.toString()));
		}
	}

	public void dispose() {
		int v;
		do {
			v = counter.get();
		} while (counter.compareAndSet(v, v - 1));

		if (counter.get() < 1) {
			IPreferenceStore pref = Activator.getGlobalPreference();
			pref.removePropertyChangeListener(this);
			for (String s : colors.keySet()) {
				Color c = colors.get(s);
				c.dispose();
			}
			colors.clear();
		}
	}

}
