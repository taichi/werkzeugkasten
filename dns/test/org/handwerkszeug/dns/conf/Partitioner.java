package org.handwerkszeug.dns.conf;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.DynamicChannelBuffer;

public class Partitioner {

	final BufferedInputStream source;

	public Partitioner(InputStream in) {
		this(new BufferedInputStream(in));
	}

	public Partitioner(BufferedInputStream source) {
		this.source = source;
	}

	enum PartitionType {
		Default, Quoted, LP, RP, Comment, Whitespace, EOL, EOF;
	}

	public static class Partition {
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

	protected ChannelBuffer working = new DynamicChannelBuffer(200);

	protected Queue<Partition> partitionQueue = new LinkedList<Partition>();

	public static final Partition EOF = new Partition(PartitionType.EOF);

	public Partition partition() {
		Partition result = this.partitionQueue.poll();
		if (result != null) {
			return result;
		}

		PartitionType type = PartitionType.Default;
		while (this.working.readable()) {
			byte ch = readByte();
			if (ch == -1) {
				return makePartition(type, 1);
			}
			if (ch == '\r') {
				byte n = readByte();
				if (n == '\n') {
					if (this.working.readerIndex() < 3) {
						this.working.discardReadBytes();
						return new Partition(PartitionType.EOL);
					}
					result = makePartition(type, 2);
					type = PartitionType.Default;
					return result;
				}
			}
			if (ch == '\n') {
				if (this.working.readerIndex() < 2) {
					this.working.discardReadBytes();
					return new Partition(PartitionType.EOL);
				}
				result = makePartition(type, 1);
				type = PartitionType.Default;
				return result;
			}

			if (ch == ';') {
				type = PartitionType.Comment;
			}

			if (type.equals(PartitionType.Comment) == false) {
				if (ch == '(') {
					this.partitionQueue.add(new Partition(PartitionType.LP));
					result = makePartition(type, 1);
					type = PartitionType.Default;
					return result;
				}
				if (ch == ')') {
					this.partitionQueue.add(new Partition(PartitionType.RP));
					result = makePartition(type, 1);
					type = PartitionType.Default;
					return result;
				}

				if ((ch == '"')) {
					while (true) {
						byte c = readByte();
						if ((c == '"') || (c == -1)) {
							type = PartitionType.Default;
							return makePartition(PartitionType.Quoted, 0);
						}
					}
				}

				if (((ch == ' ') || (ch == '\t'))) {
					if (1 < this.working.readerIndex()) {
						result = makePartition(type, 1);
					}
					while (true) {
						byte c = readByte();
						if ((c != ' ') && (c != '\t')) {
							Partition ws = makePartition(
									PartitionType.Whitespace, 1);
							if (result != null) {
								this.partitionQueue.add(ws);
							} else {
								result = ws;
							}
							break;
						}
					}
					type = PartitionType.Default;
					return result;
				}
			}

		}
		return EOF;
	}

	protected byte readByte() {
		try {
			if (this.working.readable() == false) {
				if (0 < this.source.available()) {
					this.working.writeBytes(this.source, 200);
				}
			}
			if (this.working.readable()) {
				return this.working.readByte();
			}
			return -1;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected Partition makePartition(PartitionType type, int stripSize) {
		int newsize = this.working.readerIndex() - stripSize;
		byte[] newone = new byte[newsize];
		this.working.getBytes(0, newone);
		this.working.readerIndex(newsize);
		this.working.discardReadBytes();
		return new Partition(type, newone);
	}
}
