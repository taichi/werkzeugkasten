package org.handwerkszeug.container.reflect;

public abstract class Module extends
		AbstractContainer<RefrectiveClass, Class<?>> {

	protected ClassLoader classLoader;

	public Module(String name, ClassLoader classLoader) {
		super(name);
		this.classLoader = classLoader;
	}

	protected Class<?> load(String className) {
		try {
			return this.classLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
