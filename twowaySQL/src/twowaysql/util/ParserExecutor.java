package twowaysql.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.antlr.runtime.Parser;
import org.antlr.runtime.tree.CommonTree;

public class ParserExecutor {

	public static Object execute(String rule, Parser parser) {
		Class<?> parserClass = parser.getClass();
		try {
			Method ruleName = parserClass.getMethod(rule);
			Object ruleReturn = ruleName.invoke(parser);
			String astString = null;
			if (ruleReturn != null
					&& 0 < ruleReturn.toString().indexOf(rule + "_return")) {
				Class<?> _return = Class.forName(parserClass.getName() + "$"
						+ rule + "_return");
				Method[] methods = _return.getDeclaredMethods();
				for (Method method : methods) {
					if (method.getName().equals("getTree")) {
						Method returnName = _return.getMethod("getTree");
						CommonTree tree = (CommonTree) returnName
								.invoke(ruleReturn);
						astString = tree.toStringTree();
					}
				}
			}
			if (astString != null) {
				return astString;
			} else if (ruleReturn != null) {
				return ruleReturn;
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
		return null;
	}
}
