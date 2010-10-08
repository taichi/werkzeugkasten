package org.handwerkszeug.dns;

import org.handwerkszeug.dns.record.AAAARecord;
import org.handwerkszeug.dns.record.ARecord;
import org.handwerkszeug.dns.record.HINFORecord;
import org.handwerkszeug.dns.record.MINFORecord;
import org.handwerkszeug.dns.record.MXRecord;
import org.handwerkszeug.dns.record.NULLRecord;
import org.handwerkszeug.dns.record.SOARecord;
import org.handwerkszeug.dns.record.SingleNameRecord;
import org.handwerkszeug.dns.record.TXTRecord;
import org.handwerkszeug.dns.record.WKSRecord;
import org.handwerkszeug.util.EnumUtil;
import org.handwerkszeug.util.VariableEnum;

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
	 * a mail destination (Obsolete - use MX)
	 */
	MD(3) {
		@Override
		public ResourceRecord newRecord() {
			return new SingleNameRecord(this);
		}
	},
	/**
	 * a mail forwarder (Obsolete - use MX)
	 */
	MF(4) {
		@Override
		public ResourceRecord newRecord() {
			return new SingleNameRecord(this);
		}
	},
	/**
	 * the canonical name for an alias
	 */
	CNAME(5) {
		@Override
		public ResourceRecord newRecord() {
			return new SingleNameRecord(this);
		}
	},
	/**
	 * marks the start of a zone of authority
	 */
	SOA(6) {
		@Override
		public ResourceRecord newRecord() {
			return new SOARecord();
		}
	},
	/**
	 * a mailbox domain name (EXPERIMENTAL)
	 */
	MB(7) {
		@Override
		public ResourceRecord newRecord() {
			return new SingleNameRecord(this);
		}
	},
	/**
	 * a mail group member (EXPERIMENTAL)
	 */
	MG(8) {
		@Override
		public ResourceRecord newRecord() {
			return new SingleNameRecord(this);
		}
	},
	/**
	 * a mail rename domain name (EXPERIMENTAL)
	 */
	MR(9) {
		@Override
		public ResourceRecord newRecord() {
			return new SingleNameRecord(this);
		}
	},
	/**
	 * a null RR (EXPERIMENTAL)
	 */
	NULL(10) {
		@Override
		public ResourceRecord newRecord() {
			return new NULLRecord();
		}
	},
	/**
	 * a well known service description
	 */
	WKS(11) {
		@Override
		public ResourceRecord newRecord() {
			return new WKSRecord();
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
	 * host information
	 */
	HINFO(13) {
		@Override
		public ResourceRecord newRecord() {
			return new HINFORecord();
		}
	},
	/**
	 * mailbox or mail list information
	 */
	MINFO(14) {
		@Override
		public ResourceRecord newRecord() {
			return new MINFORecord();
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
			return new TXTRecord();
		}
	},
	/**
	 * IP6 Address
	 */
	AAAA(28) {
		@Override
		public ResourceRecord newRecord() {
			return new AAAARecord();
		}
	},
	/**
	 * 
	 */
	UNKNOWN(-1) {
		@Override
		public ResourceRecord newRecord() {
			return new NULLRecord();
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
		return EnumUtil.find(Type.values(), code, UNKNOWN);
	}
}
