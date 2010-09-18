package org.handwerkszeug.dns;

import java.util.ArrayList;
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

	public static byte[] NULL_LABEL = new byte[] { 0 }; // TODO how to use?

	protected byte[] name;

	public Name(ChannelBuffer buffer) {
		this.name = this.parse(buffer);
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

		buffer.readerIndex(readPosition);
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
}
