package org.handwerkszeug.dns.conf;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import org.handwerkszeug.dns.conf.Partition.PartitionType;
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

	static final int INITIAL_BUFFER_SIZE = 200;
	protected ChannelBuffer working = new DynamicChannelBuffer(
			INITIAL_BUFFER_SIZE);

	protected Queue<Partition> partitionQueue = new LinkedList<Partition>();

	public static final Partition EOF = new Partition(PartitionType.EOF);

	public static final Partition EOL = new Partition(PartitionType.EOL);

	public static final Partition LP = new Partition(PartitionType.LP);

	public static final Partition RP = new Partition(PartitionType.RP);

	public Partition partition() {
		Partition result = this.partitionQueue.poll();
		if (result != null) {
			return result;
		}
		while (true) {
			byte ch = readByte();
			if (ch == -1) {
				if (0 < this.working.readerIndex()) {
					result = makePartition(PartitionType.Default, 0);
					discardBefore(0);
					return result;
				}
				break;
			}
			if (ch == '\r') {
				byte n = readByte();
				if (n == '\n') {
					if (this.working.readerIndex() < 3) {
						this.working.discardReadBytes();
						return EOL;
					}
					this.partitionQueue.add(EOL);
					result = makePartition(PartitionType.Default, 2);
					discardBefore(0);
				} else {
					this.working.readerIndex(this.working.readerIndex() - 1);
				}
			}
			if (ch == '\n') {
				if (this.working.readerIndex() < 2) {
					this.working.discardReadBytes();
					return EOL;
				}
				this.partitionQueue.add(EOL);
				result = makePartition(PartitionType.Default, 1);
				discardBefore(0);
			}

			if (ch == ';') {
				result = readTo(partitionBefore(), '\n');
			}

			if (ch == '(') {
				this.partitionQueue.add(LP);
				result = makePartition(PartitionType.Default, 1);
				discardBefore(0);
			}
			if (ch == ')') {
				this.partitionQueue.add(RP);
				result = makePartition(PartitionType.Default, 1);
				discardBefore(0);
			}

			if ((ch == '"')) {
				result = readTo(partitionBefore(), '"');
			}

			if (((ch == ' ') || (ch == '\t'))) {
				result = partitionBefore();
				int begin = this.working.readerIndex() - 1;
				while (true) {
					byte c = readByte();
					if ((c != ' ') && (c != '\t')) {
						int end = this.working.readerIndex() - 1;
						Partition ws = makePartition(PartitionType.Whitespace,
								begin, end);
						discardBefore(1);
						if (result != null) {
							this.partitionQueue.add(ws);
						} else {
							result = ws;
						}
						break;
					}
				}
			}
			if (result != null) {
				return result;
			}
		}
		return EOF;
	}

	protected Partition readTo(Partition back, char stop) {
		int begin = this.working.readerIndex() - 1;
		while (true) {
			byte c = readByte();
			if ((c == stop) || (c == -1)) {
				int end = this.working.readerIndex();
				Partition p = makePartition(PartitionType.Quoted, begin, end);
				discardBefore(0);
				if (back != null) {
					this.partitionQueue.add(p);
				} else {
					back = p;
				}
				break;
			}
		}
		return back;
	}

	protected byte readByte() {
		try {
			if (this.working.readable() == false) {
				if (0 < this.source.available()) {
					this.working.writeBytes(this.source, INITIAL_BUFFER_SIZE);
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

	protected Partition makePartition(PartitionType type, int begin, int end) {
		byte[] newone = new byte[end - begin];
		this.working.getBytes(begin, newone);
		return new Partition(type, newone);
	}

	protected Partition makePartition(PartitionType type, int stripSize) {
		int newsize = this.working.readerIndex() - stripSize;
		byte[] newone = new byte[newsize];
		this.working.getBytes(0, newone);
		return new Partition(type, newone);
	}

	protected Partition partitionBefore() {
		if (1 < this.working.readerIndex()) {
			return makePartition(PartitionType.Default, 1);
		}
		return null;
	}

	protected void discardBefore(int backSize) {
		this.working.readerIndex(this.working.readerIndex() - backSize);
		this.working.discardReadBytes();
	}
}
