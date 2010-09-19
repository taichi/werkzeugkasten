package org.handwerkszeug.dns;

import org.handwerkszeug.EnumUtil;
import org.handwerkszeug.VariableEnum;
import org.handwerkszeug.dns.record.ARecord;
import org.handwerkszeug.dns.record.MXRecord;
import org.handwerkszeug.dns.record.SingleNameRecord;

/**
 * 3.2.2. TYPE values
 * 
 * @author taichi
 * 
 */
public enum Type implements VariableEnum {
	/**
	 * a host address
	 */
	A(1) {
		@Override
		public ResourceRecord newRecord() {
			return new ARecord();
		}
	},
	/**
	 * an authoritative name server
	 */
	NS(2) {
		@Override
		public ResourceRecord newRecord() {
			return new SingleNameRecord(this);
		}
	},
	/**
	 * a domain name pointer
	 */
	PTR(12) {
		@Override
		public ResourceRecord newRecord() {
			return new SingleNameRecord(this);
		}
	},
	/**
	 * mail exchange
	 */
	MX(15) {
		@Override
		public ResourceRecord newRecord() {
			return new MXRecord();
		}
	},
	/**
	 * text strings
	 */
	TXT(16) {
		@Override
		public ResourceRecord newRecord() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	private int code;

	private Type(int i) {
		this.code = i;
	}

	public abstract ResourceRecord newRecord();

	@Override
	public int value() {
		return this.code;
	}

	public static Type valueOf(int code) {
		return EnumUtil.find(Type.values(), code);
	}
}
