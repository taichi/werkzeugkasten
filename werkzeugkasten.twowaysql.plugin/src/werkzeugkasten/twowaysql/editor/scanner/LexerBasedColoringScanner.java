package werkzeugkasten.twowaysql.editor.scanner;

import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.BEGIN;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.ELSE;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.ELSEIF;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.END;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.IDENT;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.IF;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.LT;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.QUOTED;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.SYMBOLS;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.SYM_BIND;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.SYM_C;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.SYM_LP;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.SYM_RP;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.WHITE_SPACES;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;

import werkzeugkasten.twowaysql.Constants.COLORING;
import werkzeugkasten.twowaysql.editor.conf.ColorDefineFactory;
import werkzeugkasten.twowaysql.grammar.NoChannelLexer;

public class LexerBasedColoringScanner implements ITokenScanner {

	protected static final Map<Integer, COLORING> typeColoring = new HashMap<Integer, COLORING>();
	static {
		typeColoring.put(BEGIN, COLORING.KEYWORD);
		typeColoring.put(IF, COLORING.KEYWORD);
		typeColoring.put(ELSE, COLORING.KEYWORD);
		typeColoring.put(ELSEIF, COLORING.KEYWORD);
		typeColoring.put(END, COLORING.KEYWORD);
		typeColoring.put(SYM_BIND, COLORING.KEYWORD);
	}

	protected boolean inExpression = false;
	protected boolean inBindType = false;

	protected CommonTokenStream tokens = null;
	protected int rangeOffset = 0;
	protected int tokenLength = 0;
	protected int tokenOffset = 0;

	protected ColorDefineFactory colorFactory;
	protected COLORING defaultColoring;

	public LexerBasedColoringScanner(COLORING defaultColoring,
			ColorDefineFactory cdf) {
		this.colorFactory = cdf;
		this.defaultColoring = defaultColoring;
	}

	@Override
	public int getTokenLength() {
		return this.tokenLength;
	}

	@Override
	public int getTokenOffset() {
		return this.rangeOffset + this.tokenOffset;
	}

	@Override
	public void setRange(IDocument document, int offset, int length) {
		try {
			String string = document.get(offset, length);
			// System.out.printf("$CLR$ %s %d %n", string, length);
			rangeOffset = offset;
			tokenLength = 0;
			tokenOffset = 0;
			inExpression = false;
			inBindType = false;
			tokens = new CommonTokenStream(new NoChannelLexer(
					new ANTLRStringStream(string)));
		} catch (BadLocationException e) {
		}
	}

	protected static final BitSet EXPRESSION_bits = new BitSet();
	static {
		EXPRESSION_bits.add(IDENT);
		EXPRESSION_bits.add(QUOTED);
		EXPRESSION_bits.add(SYMBOLS);
		EXPRESSION_bits.add(SYM_BIND);
		EXPRESSION_bits.add(SYM_C);
		EXPRESSION_bits.add(SYM_LP);
		EXPRESSION_bits.add(SYM_RP);
		EXPRESSION_bits.add(WHITE_SPACES);
		EXPRESSION_bits.add(LT);
	}

	@Override
	public IToken nextToken() {
		CommonToken ct = (CommonToken) tokens.LT(1);
		int type = ct.getType();
		if (type == org.antlr.runtime.Token.EOF) {
			return Token.EOF;
		}

		this.tokenOffset = ct.getStartIndex();
		this.tokenLength = ct.getStopIndex() - ct.getStartIndex() + 1;

		// System.out.printf("%s %s [%d:%d]%n", ct,
		// TwoWaySqlParser.tokenNames[ct
		// .getType()], this.tokenOffset, this.tokenLength);
		IToken result = null;
		COLORING c = typeColoring.get(type);
		if (c == null) {
			c = this.defaultColoring;
		}
		result = colorFactory.getToken(c);

		if (inExpression == false && (type == IF || type == ELSEIF)) {
			inExpression = true;
		} else if (inExpression) {
			while (EXPRESSION_bits.member(type)) {
				tokens.consume();
				ct = (CommonToken) tokens.LT(1);
				type = ct.getType();
			}
			CommonToken stop = (CommonToken) tokens.LT(-1);
			this.tokenLength = stop.getStopIndex() - this.tokenOffset + 1;
			inExpression = false;
			return colorFactory.getToken(COLORING.EXPRESSION);
		} else if (type == WHITE_SPACES) {
			result = Token.WHITESPACE;
		} else if (inBindType == false && type == SYM_BIND) {
			CommonToken lt2 = (CommonToken) tokens.LT(3);
			this.inBindType = lt2.getType() == SYM_BIND;
			this.inExpression = this.inBindType == false;
		} else if (inBindType) {
			inBindType = false;
			result = colorFactory.getToken(COLORING.BIND_TYPE);
		}
		tokens.consume();
		return result;
	}
}
