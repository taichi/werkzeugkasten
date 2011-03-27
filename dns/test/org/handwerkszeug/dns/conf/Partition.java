package org.handwerkszeug.dns.conf;


public class Partition {
	enum PartitionType {
		Default, Quoted, LP, RP, Comment, Whitespace, EOL, EOF;
	}

	final PartitionType type;
	final byte[] division;

	public Partition(PartitionType type) {
		this(type, null);
	}

	public Partition(PartitionType type, byte[] buffer) {
		this.type = type;
		this.division = buffer;
	}

	public PartitionType type() {
		return this.type;
	}

	public byte[] division() {
		return this.division;
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append("[");
		stb.append(this.type);
		stb.append("]<");
		if (this.division == null) {
			stb.append("null");
		} else {
			stb.append(new String(this.division));
		}
		stb.append(">");
		return stb.toString();
	}
}