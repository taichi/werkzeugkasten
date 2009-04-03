package werkzeugkasten.twowaysql.grammar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Token;
import org.antlr.runtime.UnwantedTokenException;
import org.junit.Test;

import werkzeugkasten.twowaysql.error.ProblemCoordinator;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser.twowaySQL_return;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeAcceptor;
import werkzeugkasten.twowaysql.tree.visitor.ToStringVisitor;

public class TwoWaySqlParserTest {

	protected String data(String path) throws Exception {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream in = null;
		try {
			in = cl.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			StringBuilder stb = new StringBuilder();
			while (br.ready()) {
				stb.append(br.readLine());
				stb.append("\r\n");
			}
			return stb.toString();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	protected twowaySQL_return runParser(String sql) throws Exception {
		System.err.println();
		System.err.println("#########################");
		TwoWaySqlParser parser = createParser(sql);
		return parser.twowaySQL();
	}

	protected TwoWaySqlParser createParser(String sql) {
		ProblemCoordinator pc = new ProblemCoordinator();
		ANTLRStringStream in = new ANTLRStringStream(sql);
		TwoWaySqlLexer lex = new TwoWaySqlLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lex);
		TwoWaySqlParser parser = new TwoWaySqlParser(tokens);
		parser.setProblemCoordinator(pc);
		return parser;
	}

	@Test
	public void runTokenStream() throws Exception {
		String string = "/*IF true || false /*ELSEIF true*/";
		CommonTokenStream tokens = new CommonTokenStream(new NoChannelLexer(
				new ANTLRStringStream(string)));
		CommonToken token = (CommonToken) tokens.LT(1);
		while (token.getType() != org.antlr.runtime.Token.EOF) {
			System.out.printf("%s %s %n", TwoWaySqlParser.tokenNames[token
					.getType()], token);
			tokens.consume();
			token = (CommonToken) tokens.LT(1);
		}
	}

	@Test
	public void testLex() throws Exception {
		String aaa = "/*IF hoge != null */";
		TwoWaySqlLexer lex = new TwoWaySqlLexer(new ANTLRStringStream(aaa));
		CommonTokenStream tokens = new CommonTokenStream(lex);
		CommonToken ct = (CommonToken) tokens.LT(1);
		tokens.consume();
		while (ct.getType() != Token.EOF) {
			System.out.printf("%d %d %s %n", ct.getStartIndex(), ct
					.getStopIndex(), ct.getText());
			ct = (CommonToken) tokens.LT(1);
			tokens.consume();
		}
	}

	@Test
	public void testfull() throws Exception {
		String sql = data("werkzeugkasten/twowaysql/grammar/test.txt");
		twowaySQL_return ret = runParser(sql);

		ToStringVisitor visitor = new ToStringVisitor(sql);
		StringBuilder stb = new StringBuilder();
		QueryTreeAcceptor.accept(ret.query, visitor, stb);
		System.out.println(stb.toString());
	}

	@Test
	public void testTwowaySQL() throws Exception {
		String sql = "select * from dual;/*";
		TwoWaySqlParser parser = createParser(sql);
		parser.twowaySQL();
	}

	@Test
	public void testTxt() throws Exception {
		TwoWaySqlParser parser = createParser("");
		parser.txt();

		ProblemCoordinator pc = parser.getProblemCoordinator();
		QueryProblem qp = pc.getAll().iterator().next();
		System.err.println(qp.getMessage());
		assertEquals(EarlyExitException.class, qp.getCause().getClass());
	}

	@Test
	public void testExpression() throws Exception {
		TwoWaySqlParser parser = createParser("");
		parser.expression();

		ProblemCoordinator pc = parser.getProblemCoordinator();
		QueryProblem qp = pc.getAll().iterator().next();
		System.err.println(qp.getMessage());
		assertEquals(EarlyExitException.class, qp.getCause().getClass());
	}

	@Test
	public void testBlockComment() throws Exception {
		assertBlockComment(EarlyExitException.class, "/*");
		assertBlockComment(MissingTokenException.class, "/");
		assertBlockComment(MissingTokenException.class, "/*hoge");
		assertBlockComment(MissingTokenException.class, "/*hoge*");
		assertBlockComment(MissingTokenException.class, "/*hoge/*");

	}

	protected void assertBlockComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.blockcomment();
		assertParser(expected, parser);
	}

	@Test
	public void testLineComment() throws Exception {
		assertLineComment(EarlyExitException.class, "--");
		assertLineComment(EarlyExitException.class, "#");
		assertLineComment(MissingTokenException.class, "-");
		assertLineComment(MissingTokenException.class, "--hoge");
		assertLineComment(MissingTokenException.class, "#hoge --");
		assertLineComment(MissingTokenException.class, "#hoge ");
	}

	protected void assertLineComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.linecomment();
		assertParser(expected, parser);
	}

	@Test
	public void testBeginComment() throws Exception {
		assertBeginComment(NoViableAltException.class, "/");
		assertBeginComment(MismatchedTokenException.class, "/*BEGI");
		assertBeginComment(MismatchedTokenException.class, "/*BEGIN");
		assertBeginComment(MissingTokenException.class, "/*BEGIN*");
		assertBeginComment(MissingTokenException.class, "/*BEGIN/*");
		assertBeginComment(MismatchedTokenException.class, "-- BEGIN ");

		assertBeginComment(NoViableAltException.class, "/*BEGIN*/aaaa");

		assertBeginComment(EarlyExitException.class, "/*BEGIN*/");
		assertBeginComment(EarlyExitException.class, "/*BEGIN*//*END*/");
	}

	protected void assertBeginComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.begincomment();
		assertParser(expected, parser);
	}

	@Test
	public void testElseIfNode() throws Exception {
		assertElseIfNode(EarlyExitException.class, "/*ELSEIF aaa*/");
		assertElseIfNode(EarlyExitException.class, "--ELSEIF bbb\n");
	}

	protected void assertElseIfNode(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.elseifnode();
		assertParser(expected, parser);
	}

	@Test
	public void testElseIfBlockComment() throws Exception {
		assertElseIfBlockComment(MismatchedTokenException.class, "/");
		assertElseIfBlockComment(MissingTokenException.class, "/*ELSI");
		assertElseIfBlockComment(MissingTokenException.class, "/*ELSEIF hoge");
		assertElseIfBlockComment(MissingTokenException.class, "/*ELSEIF hoge*");
	}

	protected void assertElseIfBlockComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.elseifblockcomment();
		assertParser(expected, parser);
	}

	@Test
	public void testElseIfLineComment() throws Exception {
		assertElseIfLineComment(MismatchedTokenException.class, "-");
		assertElseIfLineComment(MissingTokenException.class, "-- ELSI");
		assertElseIfLineComment(MissingTokenException.class, "--ELSEIF hoge");
		assertElseIfLineComment(MissingTokenException.class, "#ELSEIF hoge*");
	}

	protected void assertElseIfLineComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.elseiflinecomment();
		assertParser(expected, parser);
	}

	@Test
	public void testElseNode() throws Exception {
		assertElseNode(EarlyExitException.class, "/*ELSE*/");
		assertElseNode(EarlyExitException.class, "/*ELSE*/\n");
	}

	protected void assertElseNode(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.elsenode();
		assertParser(expected, parser);
	}

	@Test
	public void testElseComment() throws Exception {
		assertElseComment(NoViableAltException.class, "/");
		assertElseComment(MismatchedTokenException.class, "/*ELS");
		assertElseComment(MissingTokenException.class, "/*ELSE*");
		assertElseComment(MissingTokenException.class, "-- ELSE ");
	}

	protected void assertElseComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.elsecomment();
		assertParser(expected, parser);
	}

	@Test
	public void testEndComment() throws Exception {
		assertEndComment(NoViableAltException.class, "/");
		assertEndComment(MismatchedTokenException.class, "/*ENP");
		assertEndComment(MissingTokenException.class, "/*END*");
		assertEndComment(MissingTokenException.class, "-- END ");
	}

	protected void assertEndComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.endcomment();
		assertParser(expected, parser);
	}

	@Test
	public void testBindComment() throws Exception {
		assertBindComment(MismatchedTokenException.class, "/*");
		assertBindComment(EarlyExitException.class, "/*?");
		assertBindComment(MismatchedTokenException.class, "/*? hoge");
		assertBindComment(MismatchedTokenException.class, "/*?hoge*");
		assertBindComment(EarlyExitException.class, "/*?hoge*/");
		assertBindComment(EarlyExitException.class, "/*?aa*?hoge*/");
	}

	protected void assertBindComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.bindcomment();
		assertParser(expected, parser);
	}

	@Test
	public void testBindingName() throws Exception {
		assertBindingName(MismatchedTokenException.class, "");
		assertBindingName(MissingTokenException.class, "?");
		assertBindingName(MissingTokenException.class, "?*");
	}

	protected void assertBindingName(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.bindingname();
		assertParser(expected, parser);
	}

	@Test
	public void testInBind() throws Exception {
		assertInBind(MismatchedTokenException.class, "I/*?hoge*/(aaa,bbb)");
		assertInBind(MissingTokenException.class, "IN/* hoge*/(aaa,bbb)");
		assertInBind(MismatchedTokenException.class, "IN/*?hoge (aaa,bbb)");
		assertInBind(MismatchedTokenException.class, "IN/*?hoge");
		assertInBind(MismatchedTokenException.class, "IN/*?hoge*/");

		assertInBind(MismatchedTokenException.class, "IN/*?hoge*/(aaa");
		assertInBind(EarlyExitException.class, "IN/*?hoge*/(aaa /*");
	}

	protected void assertInBind(Class<?> expected, String sql) throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.inbind();
		assertParser(expected, parser);
	}

	@Test
	public void testInBindSkipped() throws Exception {
		assertInBindSkipped(MissingTokenException.class, "?");
		assertInBindSkipped(MissingTokenException.class, "? aaa)");
		assertInBindSkipped(EarlyExitException.class, "(");
		assertInBindSkipped(EarlyExitException.class, "(aaa,");
		assertInBindSkipped(EarlyExitException.class, "('aaa',,");
		assertInBindSkipped(MissingTokenException.class, "(aaa,bbb");
		assertInBindSkipped(MissingTokenException.class, "(aaa(");
	}

	protected void assertInBindSkipped(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.inbindskipped();
		assertParser(expected, parser);
	}

	@Test
	public void testIfComment() throws Exception {
		assertIfComment(MismatchedTokenException.class, "/");
		assertIfComment(MismatchedTokenException.class, "/* ");
		assertIfComment(MissingTokenException.class, "/*I ");
		assertIfComment(MissingTokenException.class, "/*I a");
		assertIfComment(MissingTokenException.class, "/*I a*/");
		assertIfComment(MissingTokenException.class, "/*I a*/");
		assertIfComment(EarlyExitException.class, "/*IF a*/");
		assertIfComment(EarlyExitException.class, "/*IF a*/ /*END*/");
		assertIfComment(NoViableAltException.class, "/*IF a*/b");
		assertIfComment(MismatchedTokenException.class, "/*IF a*/b/*en");
		assertIfComment(MismatchedTokenException.class, "/*IF a*/b/*END");
		assertIfComment(MismatchedTokenException.class, "/*IF a*/b--END");

		assertIfComment(EarlyExitException.class, "/*IF a*/AAA/*ELSEIF ");
		assertIfComment(MismatchedTokenException.class, "/*IF a*/AAA/*ELSEIF *");
		assertIfComment(EarlyExitException.class, "/*IF a*/AAA/*ELSEIF */");
		assertIfComment(MismatchedTokenException.class,
				"/*IF a*/AAA/*ELSEIF aa");
		assertIfComment(EarlyExitException.class, "/*IF a*/AAA/*ELSEIF aa*/");
		assertIfComment(NoViableAltException.class,
				"/*IF a*/AAA/*ELSEIF aa*/aaa");
		assertIfComment(MismatchedTokenException.class,
				"/*IF a*/AAA/*ELSEIF aa*/aaa/*");
		assertIfComment(MismatchedTokenException.class,
				"/*IF a*/AAA/*ELSEIF aa*/aaa/*END");
		assertIfComment(MismatchedTokenException.class,
				"/*IF a*/AAA/*ELSEIF aa*/aaa/*END*");

		assertIfComment(MismatchedTokenException.class, "/*IF a*/AAA/*ELSE");
		assertIfComment(MissingTokenException.class, "/*IF a*/AAA/*ELSE*");
		assertIfComment(MissingTokenException.class, "/*IF a*/AAA/*ELSE a");
		assertIfComment(EarlyExitException.class, "/*IF a*/AAA --ELSE\n");
		assertIfComment(EarlyExitException.class, "/*IF a*/AAA/*ELSE*/ /*END*/");

		assertIfComment(EarlyExitException.class,
				"/*IF a*/AAA/*ELSEIF aaa*/ /*ELSE*/");
		assertIfComment(EarlyExitException.class,
				"/*IF a*/AAA/*ELSEIF aaa*/ /*ELSE*//*END*/");
		assertIfComment(EarlyExitException.class,
				"/*IF a*/AAA/*ELSEIF aaa*/bb/*ELSE*/");
		assertIfComment(NoViableAltException.class,
				"/*IF a*/AAA/*ELSEIF aaa*/bbb/*ELSE*/cc");
		assertIfComment(MismatchedTokenException.class,
				"/*IF a*/AAA/*ELSEIF aaa*/bbb/*ELSE*/cc/*");
		assertIfComment(UnwantedTokenException.class,
				"/*IF 000 */AAA/*ELSE IF */bbb/*ELSE*/cc/*END*/");
	}

	protected void assertIfComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.ifcomment();
		assertParser(expected, parser);
	}

	protected void assertParser(Class<?> expected, TwoWaySqlParser parser) {
		ProblemCoordinator pc = parser.getProblemCoordinator();
		Iterator<QueryProblem> iterator = pc.getAll().iterator();
		assertTrue(iterator.hasNext());
		QueryProblem qp = iterator.next();
		if (qp.getMessage() == null) {
			qp.getCause().printStackTrace();
			throw new AssertionError();
		}
		System.err.println(qp.getMessage());
		assertEquals(expected, qp.getCause().getClass());
		while (iterator.hasNext()) {
			qp = iterator.next();
			System.err.println("++" + qp.getMessage());
			if (qp.getMessage() == null) {
				qp.getCause().printStackTrace();
				throw new AssertionError();
			}
		}
	}
}
