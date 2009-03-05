package werkzeugkasten.twowaysql.plugin.mvel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mvel2.MVEL;
import org.mvel2.Macro;
import org.mvel2.MacroProcessor;
import org.mvel2.ParserContext;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;

import werkzeugkasten.twowaysql.plugin.mvel.importtest2.ImportDto;

public class MvelTest {

	@Test
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
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("obj", h);
		Serializable compiled = MVEL.compileExpression(parsedExpression);
		MVEL.executeExpression(compiled, m);
		System.out.println(h.value);
	}

	public static class Hoge {
		protected String value = "hoge";

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
		ctx.setStrongTyping(false);
		ctx.setCompiled(true);

		// type mappings
		ctx.addInput("str", String.class);
		// ctx.addInput("hoge", Hoge.class);

		// import
		ctx.addPackageImport("werkzeugkasten.twowaysql.plugin.mvel.importtest");
		ctx.addImport(Hoge.class);
		ctx.addImport(ImportDto.class);
		ctx.addImport("now", MVEL.getStaticMethod(System.class,
				"currentTimeMillis", null));
		String string = "hoge = new Hoge();str = 'moge';now();hoge.value";
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
}
