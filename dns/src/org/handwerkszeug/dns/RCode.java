package org.handwerkszeug.dns;

import org.handwerkszeug.EnumUtil;
import org.handwerkszeug.VariableEnum;

/**
 * Response code - this 4 bit field is set as part of responses. The values have
 * the following interpretation:
 * 
 * @author taichi
 */
public enum RCode implements VariableEnum {

	/**
	 * No error condition
	 */
	NO_ERROR(0),

	/**
	 * Format error - The name server was unable to interpret the query.
	 */
	FORMAT_ERROR(1),

	/**
	 * Server failure - The name server was unable to process this query due to
	 * a problem with the name server.
	 */
	SERVER_FAILURE(2),

	/**
	 * Name Error - Meaningful only for responses from an authoritative name
	 * server, this code signifies that the domain name referenced in the query
	 * does not exist.
	 */
	NAME_ERROR(3),

	/**
	 * Not Implemented - The name server does not support the requested kind of
	 * query.
	 */
	NOT_IMPLEMENTED(4),

	/**
	 * Refused - The name server refuses to perform the specified operation for
	 * policy reasons. For example, a name server may not wish to provide the
	 * information to the particular requester, or a name server may not wish to
	 * perform a particular operation (e.g., zone transfer) for particular data.
	 */
	REFUSED(5);

	private int code;

	@Override
	public int value() {
		return this.code;
	}

	private RCode(int i) {
		this.code = i;
	}

	public static RCode valueOf(int code) {
		return EnumUtil.find(RCode.values(), code);
	}
}
