package org.handwerkszeug.dns;

import org.handwerkszeug.EnumUtil;
import org.handwerkszeug.VariableEnum;

/**
 * A four bit field that specifies kind of query in this message. This value is
 * set by the originator of a query and copied into the response. The values
 * are:
 * 
 * @author taichi
 */
public enum OpCode implements VariableEnum {
	/**
	 * a standard query
	 */
	QUERY(0),

	/**
	 * an inverse query
	 */
	IQUERY(1),

	/**
	 * a server status request
	 */
	STATUS(2);

	private int code;

	private OpCode(int i) {
		this.code = i;
	}

	@Override
	public int value() {
		return code;
	}

	public static OpCode valueOf(int code) {
		return EnumUtil.find(OpCode.values(), code);
	}
}