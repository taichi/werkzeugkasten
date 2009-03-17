/**
 * 
 */
package werkzeugkasten.twowaysql.editor.conf;

import werkzeugkasten.common.util.StringUtil;

public class Variable {
	String name = "";
	String type = "";
	String example = "";

	public String name() {
		return name;
	}

	public void name(String name) {
		this.name = StringUtil.toString(name);
	}

	public String type() {
		return type;
	}

	public void type(String type) {
		this.type = StringUtil.toString(type);
	}

	public String example() {
		return this.example;
	}

	public void example(String example) {
		this.example = StringUtil.toString(example);
	}

	@Override
	public String toString() {
		return String.format("{name=[%s] type=[%s] example=[%s]}", name,
				type, example);
	}
}