package org.handwerkszeug.container.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.handwerkszeug.container.Acceptor;
import org.handwerkszeug.container.Accessible;
import org.handwerkszeug.container.Container;
import org.handwerkszeug.container.ContainerFactory;
import org.handwerkszeug.container._;

public class RefrectiveClassFactory implements
		ContainerFactory<String, Accessible, String> {
	protected Module module;

	public RefrectiveClassFactory(Module module) {
		this.module = module;
	}

	@Override
	public Container<Accessible, String> create(String className) {
		Class<?> clazz = this.module.load(className);
		return create(clazz);
	}

	protected Container<Accessible, String> create(Class<?> context) {
		RefrectiveClass result = this.module.accept(context,
				new Acceptor<RefrectiveClass, RefrectiveClass>() {
					@Override
					public RefrectiveClass accept(RefrectiveClass content) {
						return content;
					}
				}, null);
		if (result == null) {
			result = new RefrectiveClass(this.module, context);
			// XXX 常にフィールドとメソッドを全部歩くのはうそだべ。
			RefrectionUtil.walkField(context, new Acceptor<Field, _>() {
				public _ accept(Field content) {
					return _._;
				};
			});
			RefrectionUtil.walkMethod(context, new Acceptor<Method, _>() {
				@Override
				public _ accept(Method content) {
					return _._;
				}
			});
			this.module.entry(context, result);
			Class<?> superClass = context.getSuperclass();
			if (superClass.getName().startsWith("java") == false) {
				create(superClass);
			}
		}
		return result;
	}

	public interface Strategy<AC extends AccessibleObject> {

		void realize(RefrectiveClass rc, AC ac);
	}

	public static class CompositeStrategy<AC extends AccessibleObject>
			implements Strategy<AC> {

		protected List<Strategy<AC>> strategies;

		public CompositeStrategy(Strategy<AC>... strategies) {
			this.strategies = Arrays.asList(strategies);
		}

		@Override
		public void realize(RefrectiveClass rc, AC ac) {
			for (Strategy<AC> s : this.strategies) {
				s.realize(rc, ac);
			}
		}
	}

	public static class JavaBeansStrategy implements Strategy<Method> {

		static final Pattern isAccessor = Pattern
				.compile("(g|s)et(\\d|\\p{Upper}).*");

		@Override
		public void realize(RefrectiveClass rc, Method ac) {
			String name = ac.getName();
			if (isAccessor.matcher(name).matches()) {
				String prop = name.substring(3);
				if (rc.accept(prop, new Acceptor<Accessible, Boolean>() {
					@Override
					public Boolean accept(Accessible content) {
						return true;
					}
				}, false) == false) {

				}
			}
		}
	}

}
