package werkzeugkasten.twowaysql.editor.scanner;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.junit.Before;
import org.junit.Test;

public class PartitionScannerTest {
	PartitionScanner target;

	class State {
		int offset;
		int length;
		IToken t;

		public State(int offset, int length, IToken t) {
			super();
			this.offset = offset;
			this.length = length;
			this.t = t;
		}
	}

	@Before
	public void setUp() throws Exception {
		this.target = new PartitionScanner();
	}

	protected List<State> partition(String string) {
		IDocument doc = new Document(string);
		this.target.setRange(doc, 0, doc.getLength());
		List<State> result = new ArrayList<State>();
		IToken cursor = this.target.nextToken();
		while (cursor != Token.EOF) {
			log(cursor);
			State s = new State(this.target.tokenOffset,
					this.target.tokenLength, cursor);
			result.add(s);
			cursor = this.target.nextToken();
		}
		return result;
	}

	protected void log(IToken token) {
		System.out.printf("%s [%s:%s]%n", token.getData(),
				this.target.tokenOffset, this.target.tokenLength);
	}

	@Test
	public void testNextToken() {
		List<State> list = partition("AAA/*bbb*/Ccc");
		assertEquals(3, list.size());
		assertEquals(0, list.get(0).offset);
		assertEquals(3, list.get(0).length);
		assertEquals(3, list.get(1).offset);
		assertEquals(7, list.get(1).length);
		assertEquals(10, list.get(2).offset);
		assertEquals(3, list.get(2).length);

		assertEquals(PartitionScanner.PT_TXT, list.get(0).t);
		assertEquals(PartitionScanner.PT_BLOCKCOMMENT, list.get(1).t);
		assertEquals(PartitionScanner.PT_TXT, list.get(2).t);

		list = partition("AAA--BBBB\nccc");
		assertEquals(3, list.size());
		assertEquals(0, list.get(0).offset);
		assertEquals(3, list.get(0).length);
		assertEquals(3, list.get(1).offset);
		assertEquals(7, list.get(1).length);
		assertEquals(10, list.get(2).offset);
		assertEquals(3, list.get(2).length);

		assertEquals(PartitionScanner.PT_TXT, list.get(0).t);
		assertEquals(PartitionScanner.PT_LINECOMMENT, list.get(1).t);
		assertEquals(PartitionScanner.PT_TXT, list.get(2).t);

		list = partition("AAA#BBBBB\nccc");
		assertEquals(3, list.size());
		assertEquals(0, list.get(0).offset);
		assertEquals(3, list.get(0).length);
		assertEquals(3, list.get(1).offset);
		assertEquals(7, list.get(1).length);
		assertEquals(10, list.get(2).offset);
		assertEquals(3, list.get(2).length);

		assertEquals(PartitionScanner.PT_TXT, list.get(0).t);
		assertEquals(PartitionScanner.PT_LINECOMMENT, list.get(1).t);
		assertEquals(PartitionScanner.PT_TXT, list.get(2).t);
	}

	@Test
	public void testNextToken2() {
		List<State> list = partition("AAA/*bbbbb");
		assertEquals(2, list.size());
		assertEquals(0, list.get(0).offset);
		assertEquals(3, list.get(0).length);
		assertEquals(3, list.get(1).offset);
		assertEquals(7, list.get(1).length);
		assertEquals(PartitionScanner.PT_TXT, list.get(0).t);
		assertEquals(PartitionScanner.PT_BLOCKCOMMENT, list.get(1).t);

		list = partition("AAA/*bbbbb/*");
		assertEquals(3, list.size());
		assertEquals(0, list.get(0).offset);
		assertEquals(3, list.get(0).length);
		assertEquals(3, list.get(1).offset);
		assertEquals(7, list.get(1).length);
		assertEquals(10, list.get(2).offset);
		assertEquals(2, list.get(2).length);
		assertEquals(PartitionScanner.PT_TXT, list.get(0).t);
		assertEquals(PartitionScanner.PT_BLOCKCOMMENT, list.get(1).t);
		assertEquals(PartitionScanner.PT_BLOCKCOMMENT, list.get(2).t);

		list = partition("AAA/*bbbbb--");
		assertEquals(3, list.size());

		assertEquals(0, list.get(0).offset);
		assertEquals(3, list.get(0).length);
		assertEquals(3, list.get(1).offset);
		assertEquals(7, list.get(1).length);
		assertEquals(10, list.get(2).offset);
		assertEquals(2, list.get(2).length);

		assertEquals(PartitionScanner.PT_TXT, list.get(0).t);
		assertEquals(PartitionScanner.PT_BLOCKCOMMENT, list.get(1).t);
		assertEquals(PartitionScanner.PT_LINECOMMENT, list.get(2).t);

		list = partition("AAA/*bbbb\n#C");
		assertEquals(3, list.size());

		assertEquals(0, list.get(0).offset);
		assertEquals(3, list.get(0).length);
		assertEquals(3, list.get(1).offset);
		assertEquals(7, list.get(1).length);
		assertEquals(10, list.get(2).offset);
		assertEquals(2, list.get(2).length);

		assertEquals(PartitionScanner.PT_TXT, list.get(0).t);
		assertEquals(PartitionScanner.PT_BLOCKCOMMENT, list.get(1).t);
		assertEquals(PartitionScanner.PT_LINECOMMENT, list.get(2).t);
	}

	@Test
	public void skipToNextQuote() throws Exception {
		List<State> list = partition("AAA/*\'bcde\'f*/gGg");
		assertEquals(3, list.size());
		assertEquals(0, list.get(0).offset);
		assertEquals(3, list.get(0).length);
		assertEquals(3, list.get(1).offset);
		assertEquals(11, list.get(1).length);
		assertEquals(14, list.get(2).offset);
		assertEquals(3, list.get(2).length);

		assertEquals(PartitionScanner.PT_TXT, list.get(0).t);
		assertEquals(PartitionScanner.PT_BLOCKCOMMENT, list.get(1).t);
		assertEquals(PartitionScanner.PT_TXT, list.get(2).t);

	}
}
