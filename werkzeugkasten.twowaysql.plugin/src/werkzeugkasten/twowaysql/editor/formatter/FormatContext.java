package werkzeugkasten.twowaysql.editor.formatter;

public class FormatContext {

	protected int depth = 0;
	protected StringBuilder buffer;
	protected String indent = "\t";
	protected String lineSep = System.getProperty("line.separator");

	public FormatContext(StringBuilder stb) {
		this.buffer = stb;
	}

	public void add(int depth) {
		this.depth += depth;
	}

	public void remove(int depth) {
		this.depth -= depth;
	}

	public void append(String string) {
		this.buffer.append(string);
	}

	public void indent() {
		for (int i = 0; i < this.depth; i++) {
			this.buffer.append(this.indent);
		}
	}

	public void separateLine() {
		this.buffer.append(this.lineSep);
	}
}
