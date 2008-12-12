package werkzeugkasten.twowaysql.dao.base;

import java.io.BufferedInputStream;
import java.io.InputStream;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.twowaysql.dao.QueryLoader;
import werkzeugkasten.twowaysql.dao.QueryWrapper;
import werkzeugkasten.twowaysql.error.ProblemCoordinator;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class DefaultQueryLoader implements QueryLoader<String> {

	public static final int BUF_SIZE = 128 * 128;

	@Override
	public QueryWrapper load(String context) {
		final String source = loadSource(context);
		return new QueryWrapper() {
			@Override
			public String getSource() {
				return source;
			}

			public <C, V extends QueryTreeVisitor<C>> void accept(V visitor,
					C context) {
				ProblemCoordinator pc = new ProblemCoordinator();
				ANTLRStringStream in = new ANTLRStringStream(source);
				TwoWaySqlLexer lex = new TwoWaySqlLexer(in);
				CommonTokenStream tokens = new CommonTokenStream(lex);
				TwoWaySqlParser parser = new TwoWaySqlParser(tokens);
				parser.setProblemCoordinator(pc);
				try {
					TwoWaySqlParser.twowaySQL_return ret = parser.twowaySQL();
					ret.query.accept(visitor, context);
				} catch (RecognitionException e) {
					// do nothing.
				}
			};
		};
	}

	protected String loadSource(final String context) {
		final StringBuilder stb = new StringBuilder();
		new Streams.using<InputStream, Exception>(Exception.class) {
			@Override
			public InputStream open() throws Exception {
				ClassLoader cl = Thread.currentThread().getContextClassLoader();
				return cl.getResourceAsStream(context);
			}

			@Override
			public void handle(InputStream stream) throws Exception {
				BufferedInputStream in = new BufferedInputStream(stream);
				byte[] buf = new byte[BUF_SIZE];
				int len = 0;
				do {
					len = in.read(buf, 0, BUF_SIZE);
					stb.append(buf);
				} while (0 < len);
			}

			@Override
			public void happen(Exception exception) {
				throw new IllegalStateException(exception);
			}
		};
		return new String(stb);
	}
}
