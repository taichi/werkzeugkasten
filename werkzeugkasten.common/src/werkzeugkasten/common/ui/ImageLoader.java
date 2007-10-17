package werkzeugkasten.common.ui;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;

import werkzeugkasten.common.resource.LogUtil;

public class ImageLoader {

	public static void load(Class<?> holder) {
		load(JFaceResources.getImageRegistry(), holder);
	}

	public static void load(ImageRegistry registry, Class<?> holder) {
		load(registry, holder, holder.getName());
	}

	public static void load(ImageRegistry registry, Class<?> holder, String name) {
		ResourceBundle bundle = getBundle(name, holder.getClassLoader());
		if (bundle == null) {
			return;
		}
		Field[] fields = holder.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (validateMask(field)) {
				continue;
			}
			String key = field.getName();
			if (bundle.containsKey(key) == false) {
				log(key + " not found in " + name);
				continue;
			}
			ImageDescriptor id = registry.getDescriptor(key);
			if (id == null) {
				id = ImageDescriptor.createFromFile(holder, bundle
						.getString(key));
				registry.put(key, id);
			} else {
				log(key + " is already registered [" + holder + "]");
			}

			try {
				if (isAssignableFrom(ImageDescriptor.class, field)) {
					field.set(null, id);
				} else if (isAssignableFrom(Image.class, field)) {
					field.set(null, registry.get(key));
				}
			} catch (Exception e) {
				log(e.getMessage());
			}
		}
	}

	private static boolean validateMask(Field f) {
		final int MOD_EXPECTED = Modifier.PUBLIC | Modifier.STATIC;
		final int MOD_MASK = MOD_EXPECTED | Modifier.FINAL;
		return (f.getModifiers() & MOD_MASK) != MOD_EXPECTED;
	}

	private static void log(String msg) {
		LogUtil.log(ResourcesPlugin.getPlugin(), msg);
	}

	private static ResourceBundle getBundle(String name, ClassLoader loader) {
		try {
			return ResourceBundle.getBundle(name, Locale.getDefault(), loader);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	private static boolean isAssignableFrom(final Class<?> clazz,
			final Field target) {
		return clazz.isAssignableFrom(target.getType());
	}

}
