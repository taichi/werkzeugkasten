package werkzeugkasten.twowaysql.editor.conf;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorManager {

	protected Map<Display, Map<RGB, Color>> colors = new HashMap<Display, Map<RGB, Color>>();

	protected Color getColor(RGB rgb) {
		final Display current = Display.getCurrent();
		Map<RGB, Color> inline = colors.get(current);
		if (inline == null) {
			inline = new HashMap<RGB, Color>();
			colors.put(current, inline);
			current.disposeExec(new Runnable() {
				@Override
				public void run() {
					dispose(current);
				}
			});
		}
		Color c = inline.get(rgb);
		if (c == null) {
			c = new Color(current, rgb);
			inline.put(rgb, c);
		}
		return c;
	}

	public void dispose(Display display) {
		Map<RGB, Color> inline = this.colors.get(display);
		if (inline != null) {
			for (Color c : inline.values()) {
				c.dispose();
			}
		}
		inline.clear();
	}
}
