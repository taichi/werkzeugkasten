package werkzeugkasten.twowaysql.plugin.mvel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.junit.Test;
import org.mvel2.MVEL;
import org.mvel2.Macro;
import org.mvel2.MacroProcessor;
import org.mvel2.ParserContext;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;

import werkzeugkasten.twowaysql.plugin.mvel.importtest2.ImportDto;

public class MvelTest {

	// @Test
	public void testMacro() throws Exception {
		String expression = "modify (obj) { value = 'foo' };";
		Macro modifyMacro = new Macro() {
			public String doMacro() {
				// return "@Modify with";
				return "with";
			}
		};
		Map<String, Macro> myMacros = new HashMap<String, Macro>();
		myMacros.put("modify", modifyMacro);
		MacroProcessor macroProcessor = new MacroProcessor(myMacros);
		String parsedExpression = macroProcessor.parse(expression);
		System.out.println(parsedExpression);

		Hoge h = new Hoge();
		Map<String, Hoge> m = new HashMap<String, Hoge>();
		m.put("obj", h);
		Serializable compiled = MVEL.compileExpression(parsedExpression);
		MVEL.executeExpression(compiled, m);
		System.out.println(h.value);
	}

	public static class Hoge {
		private String value = "hoge";

		public void setValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	@Test
	public void testImport() throws Exception {
		ParserContext ctx = new ParserContext();
		ctx.setStrongTyping(true);
		ctx.setCompiled(true);

		// type mappings
		ctx.addInput("str", String.class);
		ctx.addInput("fuga", Fuga.class);
		// ctx.addInput("hoge", Hoge.class);

		// import
		ctx.addPackageImport("werkzeugkasten.twowaysql.plugin.mvel.importtest");
		ctx.addImport(Fuga.class);
		ctx.addImport(ImportDto.class);
		ctx.addImport("now", MVEL.getStaticMethod(System.class,
				"currentTimeMillis", null));
		String string = "fuga.fuga != null";

		ExpressionCompiler compiler = new ExpressionCompiler(string);
		CompiledExpression expression = compiler.compile(ctx);

		// this is return type.
		Class<?> lastType = expression.getKnownEgressType();
		System.out.println(lastType);

		ParserContext parsedCtx = expression.getParserContext();
		ParserContext compileState = compiler.getParserContextState();
		System.out.printf("parsed :[%d] / compile:[%d]%n", System
				.identityHashCode(parsedCtx), System
				.identityHashCode(compileState));
		System.out.println(compileState.getVariables());
		System.out.println(compileState.getInputs());

		// if (Class.class.equals(lastType)) {
		// System.out.println("STATIC !!");
		// }
		//
		// if (lastType == null || Object.class.equals(lastType)
		// || Class.class.equals(lastType)) {
		// System.out.println("PropertyVerifier !!");
		// PropertyVerifier pv = new PropertyVerifier(string, compileState);
		// lastType = pv.analyze();
		// }
		// System.out.println(lastType);

		// Map<String, String> vars = new HashMap<String, String>();
		// System.out.println(expression.getValue(new Hoge(),
		// new MapVariableResolverFactory(vars)));
		// System.out.println(vars);
	}

	public static class Fuga {
		String value = "fugafuga";

		public String getFuga() {
			return this.value;
		}

		public void setFuga(String s) {
			this.value = s;
		}
	}

	@Test
	public void testSplit() throws Exception {
		String exp = "aaa = 'aaa';hoge.fuga(10,true);"
				// + " foreach (x : 9) {System.out.print(x); }";
				+ " hogehoge.map = ['piro':0,'piropiro':1];"
				+ "hogehoge.?hoge.value;" + "";
		System.out.printf("<<%s>>%n", getInnerExpression(exp));
		String s = "hoge<=";
		System.out.println(s.substring(0, s.length() - 2));
	}

	public static String getInnerExpression(String backText) {
		String last = getLastExpression(backText).trim();
		System.out.println(last);
		char[] c = last.toCharArray();
		int start = 0;
		for (int i = c.length - 1; i >= 0; i--) {
			if (Character.isWhitespace(c[i]) || c[i] == '(' || c[i] == '+'
					|| c[i] == ')' || c[i] == '[' || c[i] == ']' || c[i] == ':'
					|| c[i] == '=' || c[i] == '<' || c[i] == '>' || c[i] == ','
					|| c[i] == '{' || c[i] == '}') {
				start = i + 1;
				break;
			}
		}
		System.out.printf("length:[%s] start:[%s] %n", last.length(), start);
		last = last.substring(start);
		return last;
	}

	public static String getLastExpression(String backText) {
		StringTokenizer st = new StringTokenizer(backText, ";");
		String last = "";
		while (st.hasMoreTokens()) {
			last = st.nextToken();
		}
		if (last.trim().length() == 0) {
			return backText;
		}
		return last;
	}

	// @Test
	public void testIn() throws Exception {
		InTest[] ary = new InTest[] { new InTest(true, "1"),
				new InTest(true, "2"), new InTest(false, "3"),
				new InTest(false, "4"), new InTest(true, "5") };
		String expression = "(value in list if $.is == false)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", Arrays.asList(ary));
		System.out.println(MVEL.eval(expression, map));
		// expression = "strings = (hoge in (inins in list))";
		// System.out.println(MVEL.eval(expression, map));
	}

	public static class InTest {
		boolean is;
		String value;
		List<InInTest> inins = new ArrayList<InInTest>();
		List<InTest> ins;

		public InTest(boolean is, String s) {
			this.is = is;
			this.value = s;
			this.inins.add(new InInTest(is + s));
			this.inins.add(new InInTest(String.valueOf(is)));
			this.inins.add(new InInTest(s));
			this.inins.add(new InInTest(s + is));
		}

		public InTest(boolean is, String s, boolean root) {
			this(is, s);
			if (root) {
				this.ins = new ArrayList<InTest>();
				this.ins.add(new InTest(is, s, false));
				this.ins.add(new InTest(is ^ false, s + is, false));
				this.ins.add(new InTest(is ^ true, is + s, false));
			}

		}

		public String getValue() {
			return value;
		}

		public boolean getIs() {
			return is;
		}

		public List<InInTest> getInins() {
			return inins;
		}

		public List<InTest> getIns() {
			return ins;
		}
	}

	public static class InInTest {
		String hoge;

		public InInTest(String s) {
			this.hoge = s;
		}

		public String getHoge() {
			return hoge;
		}

		@Override
		public String toString() {
			return "InInTest#" + hoge;
		}
	}

	// @Test
	public void testLangugageLevel() throws Exception {
		try {
			AbstractParser
					.setLanguageLevel(AbstractParser.LEVEL_5_CONTROL_FLOW);

			String exp = "threshold = def (x) { x >= 10 ? x : 0 };"
					+ "System.out.println(threshold(11));"
					+ " foreach (x : 9) {System.out.print(x); };"
					+ " hogehoge.map = ['piro':0,'piropiro':1];hogehoge.?hoge.value;"
					+ "System.out.println();";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hogehoge", new HogeHoge());
			System.out.println(MVEL.eval(exp, map));

			Serializable compiled = MVEL.compileExpression(exp);
			System.out.println(MVEL.executeExpression(compiled, map));
			System.out.println(map);
		} finally {
			AbstractParser
					.setLanguageLevel(AbstractParser.LEVEL_5_CONTROL_FLOW);
		}
	}

	public static class HogeHoge {
		Hoge hoge = null;
		Map<String, Object> map = null;

		public Map<String, Object> getMap() {
			return map;
		}

		public Hoge getHoge() {
			return hoge;
		}

		public void setMap(Map<String, Object> map) {
			this.map = map;
		}

		@Override
		public String toString() {
			return "hoge[" + hoge + "] " + map;
		}
	}
}
