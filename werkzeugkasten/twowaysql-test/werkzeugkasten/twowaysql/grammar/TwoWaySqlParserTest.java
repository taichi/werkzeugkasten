package werkzeugkasten.twowaysql.grammar;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.junit.Test;

import werkzeugkasten.twowaysql.error.ProblemCoordinator;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser.twowaySQL_return;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeAcceptor;
import werkzeugkasten.twowaysql.tree.visitor.ToStringVisitor;
import werkzeugkasten.twowaysql.tree.visitor.TxtDealingVisitor;

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
	public void testfull() throws Exception {
		String sql = data("werkzeugkasten/twowaysql/grammar/test.txt");
		twowaySQL_return ret = runParser(sql);

		TxtDealingVisitor dealer = new TxtDealingVisitor();
		QueryTreeAcceptor.accept(ret.query, dealer, sql);

		ToStringVisitor visitor = new ToStringVisitor();
		StringBuilder stb = new StringBuilder();
		QueryTreeAcceptor.accept(ret.query, visitor, stb);
		System.out.println(stb.toString());
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
		assertLineComment(MissingTokenException.class, "#hoge ");
	}

	protected void assertLineComment(Class<?> expected, String sql)
			throws Exception {
		TwoWaySqlParser parser = createParser(sql);
		parser.linecomment();
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

	protected void assertParser(Class<?> expected, TwoWaySqlParser parser) {
		ProblemCoordinator pc = parser.getProblemCoordinator();
		Iterator<QueryProblem> iterator = pc.getAll().iterator();
		QueryProblem qp = iterator.next();
		System.err.println(qp.getMessage());
		assertEquals(expected, qp.getCause().getClass());
		while (iterator.hasNext()) {
			qp = iterator.next();
			System.err.println("++" + qp.getMessage());
		}
	}
}
