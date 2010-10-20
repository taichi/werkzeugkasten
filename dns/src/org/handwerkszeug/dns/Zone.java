package org.handwerkszeug.dns;

public class Zone {

	protected String name;

	protected String type;

	protected String file;

	public String name() {
		return this.name;
	}

	public void name(String name) {
		this.name = name;
	}

	public String type() {
		return this.type;
	}

	public void type(String type) {
		this.type = type;
	}

	public String file() {
		return this.file;
	}

	public void file(String file) {
		this.file = file;
	}

}
