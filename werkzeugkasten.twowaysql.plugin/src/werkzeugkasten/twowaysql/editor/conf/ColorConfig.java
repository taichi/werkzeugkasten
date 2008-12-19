package werkzeugkasten.twowaysql.editor.conf;

public class ColorConfig {

	protected String type;
	protected int style;

	public ColorConfig(String type, int style) {
		this.type = type;
		this.style = style;
	}

	public String type() {
		return this.type;
	}

	public int style() {
		return this.style;
	}
}
