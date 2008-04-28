package twowaysql.el;

import java.util.ServiceLoader;

public abstract class ExpressionFactory {

	protected static ServiceLoader<ExpressionFactory> factoryLoader;

	protected static ExpressionFactory instance;

	static {
		factoryLoader = ServiceLoader.load(ExpressionFactory.class);
		instance = factoryLoader.iterator().next();
		instance.initialize();
	}

	public abstract void initialize();

	public abstract void dispose();

	public static ExpressionFactory instance() {
		return instance;
	}

	public abstract Expression factory(String src);
}
