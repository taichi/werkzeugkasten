package org.handwerkszeug.dns;

import org.jboss.netty.buffer.ChannelBuffer;

public class RecordUtil {

	/**
	 * 3.3. Standard RRs
	 * <p>
	 * &lt;character-string&gt; is a single length octet followed by that number
	 * of characters. &lt;character-string&gt; is treated as binary information,
	 * and can be up to 256 characters in length (including the length octet).
	 * </p>
	 * 
	 * @param buffer
	 * @return
	 */
	public static byte[] readString(ChannelBuffer buffer) {
		short length = buffer.readUnsignedByte();
		byte[] newone = new byte[length];
		buffer.readBytes(newone);
		return newone;
	}
}
