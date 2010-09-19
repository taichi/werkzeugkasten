package org.handwerkszeug.dns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

public class Name {

	/**
	 * 4.1.4. Message compression
	 */
	public static final int MASK_POINTER = 0xC0; // 1100 0000

	/**
	 * 2.3.1. Preferred name syntax
	 */
	public static final int LABEL_MAX = 63; // 0011 1111

	public static byte[] NULL_LABEL = new byte[] { 0 };

	protected byte[] name;

	public Name(ChannelBuffer buffer) {
		this.name = this.parse(buffer);
	}

	protected Name(byte[] name) {
		this.name = name;
	}

	protected byte[] parse(ChannelBuffer buffer) {
		List<byte[]> list = new ArrayList<byte[]>();
		int readPosition = 0;
		boolean jumped = false;

		for (int length = buffer.readUnsignedByte(); 0 < length; length = buffer
				.readUnsignedByte()) {
			if ((length & MASK_POINTER) != 0) {
				int p = ((length ^ MASK_POINTER) << 8)
						+ buffer.readUnsignedByte();
				if (jumped == false) {
					readPosition = buffer.readerIndex();
					jumped = true;
				}
				buffer.readerIndex(p);
			} else if (length <= LABEL_MAX) {
				byte[] ary = new byte[length];
				buffer.readBytes(ary);
				list.add(ary);
			} else {
				throw new IllegalStateException("Invalid compression mask : "
						+ length);
			}
		}

		if (jumped) {
			buffer.readerIndex(readPosition);
		}

		return freeze(buffer, list);
	}

	protected byte[] freeze(ChannelBuffer buffer, List<byte[]> list) {
		int size = 0;
		for (byte[] a : list) {
			size += a.length;
			size += 1;
		}
		byte[] result = new byte[size];
		int pos = 0;
		for (byte[] a : list) {
			System.arraycopy(a, 0, result, pos, a.length);
			pos += a.length;
			result[pos] = '.';
			pos++;
		}
		return result;
	}

	public void write(ChannelBuffer buffer, NameCompressor compressor) {
		if (write(buffer, compressor, this) == false) {
			compressor.put(this, buffer.writerIndex());
			List<byte[]> list = split();
			int namelength = name.length;
			int consumed = 0;
			for (Iterator<byte[]> i = list.iterator(); i.hasNext();) {
				byte[] current = i.next();
				consumed += (current.length + 1);
				byte[] newone = new byte[namelength - consumed];
				System.arraycopy(this.name, consumed, newone, 0, newone.length);
				Name n = new Name(newone);
				if (write(buffer, compressor, n)) {
					break;
				}
				compressor.put(n, buffer.writerIndex());
				buffer.writeByte(current.length);
				buffer.writeBytes(current);
				if (i.hasNext() == false) {
					buffer.writeBytes(NULL_LABEL);
				}
			}
		}
	}

	protected boolean write(ChannelBuffer buffer, NameCompressor compressor,
			Name n) {
		int position = compressor.get(n);
		if (-1 < position) {
			int pointer = (MASK_POINTER << 8) | position;
			buffer.writeShort(pointer);
			return true;
		}
		return false;
	}

	protected List<byte[]> split() {
		List<byte[]> result = new ArrayList<byte[]>();
		for (int i = 0, begin = 0, part = 0, length = this.name.length; i < length; i++) {
			byte b = this.name[i];
			if (b == '.') {
				byte[] newone = new byte[part];
				System.arraycopy(this.name, begin, newone, 0, part);
				result.add(newone);
				part = 0;
				begin = ++i;
			} else {
				part++;
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Name) {
			return equals((Name) obj);
		}
		return false;
	}

	public boolean equals(Name other) {
		return other != null && Arrays.equals(this.name, other.name);
	}
}
