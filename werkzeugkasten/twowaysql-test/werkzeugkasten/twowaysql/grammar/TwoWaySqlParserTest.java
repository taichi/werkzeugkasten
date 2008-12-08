package werkzeugkasten.twowaysql.grammar;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.EarlyExitException;
import org.junit.Test;

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
	public void testCharactors() throws Exception {
		TwoWaySqlParser parser = createParser("");
		parser.charactors();

		ProblemCoordinator pc = parser.getProblemCoordinator();
		QueryProblem qp = pc.getAll().iterator().next();
		System.out.println(qp.getMessage());
		assertEquals(EarlyExitException.class, qp.getCause().getClass());
	}

	// @Test
	// public void testIfError() throws Exception {
	// String sql = "SELECT /*IF true";
	// runParser(sql);
	// sql = "SELECT /*IF true*/";
	// runParser(sql);
	// sql = "SELECT /*IF true*/* FROM HOGE;";
	// runParser(sql);
	// }
}
