package org.handwerkszeug.dns;

import java.io.DataInput;
import java.io.IOException;

/**
 * RFC1035 4.1.1. Header section format
 * 
 * <pre>
 *                                     1  1  1  1  1  1
 *       0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *     |                      ID                       |
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *     |QR|   Opcode  |AA|TC|RD|RA|   Z    |   RCODE   |
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *     |                    QDCOUNT                    |
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *     |                    ANCOUNT                    |
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *     |                    NSCOUNT                    |
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *     |                    ARCOUNT                    |
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * </pre>
 */
public class Header {

	static final int MIN_16BitValue = 0;
	static final int MAX_16BitValue = 0xFFFF;

	static final int FLAGS_QR = 15;
	static final int FLAGS_Opcode = 11;
	static final int FLAGS_AA = 10;
	static final int FLAGS_TC = 9;
	static final int FLAGS_RD = 8;
	static final int FLAGS_RA = 7;
	static final int FLAGS_Z = 4;
	static final int FLAGS_RCODE = 0;

	protected int id;
	// include there flags |QR| Opcode |AA|TC|RD|RA| Z | RCODE|
	protected int flags;

	protected int qdcount;
	protected int ancount;
	protected int nscount;
	protected int arcount;

	public Header() {
		this(MIN_16BitValue);
	}

	public Header(int id) {
		id(id);
	}

	public Header(DataInput in) throws IOException {
		id(in.readShort());
	}

	public static Header parse(DataInput di) throws IOException {
		Header result = new Header(di.readShort());
		result.flags(di.readShort());
		result.qdcount(di.readShort());
		result.ancount(di.readShort());
		result.nscount(di.readShort());
		result.arcount(di.readShort());
		return result;
	}

	/**
	 * A 16 bit identifier assigned by the program that generates any kind of
	 * query. This identifier is copied the corresponding reply and can be used
	 * by the requester to match up replies to outstanding queries.
	 * 
	 */
	public int id() {
		return this.id;
	}

	private void id(int i) {
		this.id = verify16bitValue("ID", i);
	}

	protected int verify16bitValue(String column, int i) {
		if (i < MIN_16BitValue || MAX_16BitValue < i) {
			// TODO ERROR Message.
			throw new IllegalArgumentException();
		}
		return i;
	}

	public void flags(int flags) {
		this.flags = verify16bitValue("Flags", flags);
	}

	protected int flags(int shift, int mask) {
		return (this.flags >> shift) & mask;
	}

	/**
	 * A one bit field that specifies whether this message is a query (0), or a
	 * response (1).
	 */
	public boolean qr() {
		return flags(FLAGS_QR, 0x1) != 0;
	}

	public void qr(boolean is) {
		flip(FLAGS_QR, is);
	}

	/**
	 * @see OpCode
	 */
	public OpCode opcode() {
		int code = flags(FLAGS_Opcode, 0xF);
		return OpCode.valueOf(code); // TODO cache?
	}

	public void opcode(OpCode op) {
		// clear current opcode
		this.flags &= 0x87FF; // 1000 0111 1111 1111
		// set opcode
		this.flags |= op.value() << FLAGS_Opcode;
	}

	/**
	 * Authoritative Answer - this bit is valid in responses, and specifies that
	 * the responding name server is an authority for the domain name in
	 * question section.
	 */
	public boolean aa() {
		return flags(FLAGS_AA, 0x1) != 0;
	}

	public void aa(boolean is) {
		flip(FLAGS_AA, is);
	}

	protected void flip(int index, boolean is) {
		int i = 1 << index; // TODO move to caller ?
		if (is) {
			this.flags |= i;
		} else {
			this.flags &= i ^ 0xFFFF;
		}
	}

	/**
	 * TrunCation - specifies that this message was truncated due to length
	 * greater than that permitted on the transmission channel.
	 */
	public boolean tc() {
		return flags(FLAGS_TC, 0x1) != 0;
	}

	public void tc(boolean is) {
		flip(FLAGS_TC, is);
	}

	/**
	 * Recursion Desired - this bit may be set in a query and is copied into the
	 * response. If RD is set, it directs the name server to pursue the query
	 * recursively. Recursive query support is optional.
	 */
	public boolean rd() {
		return flags(FLAGS_RD, 0x1) != 0;
	}

	public void rd(boolean is) {
		flip(FLAGS_RD, is);
	}

	/**
	 * Recursion Available - this be is set or cleared in a response, and
	 * denotes whether recursive query support is available in the name server.
	 */
	public boolean ra() {
		return flags(FLAGS_RA, 0x1) != 0;
	}

	public void ra(boolean is) {
		flip(FLAGS_RA, is);
	}

	/**
	 * Reserved for future use. Must be zero in all queries and responses.
	 */
	public int z() {
		return flags(FLAGS_Z, 0x7);
	}

	/**
	 * @see RCode
	 */
	public RCode rcode() {
		int code = flags(FLAGS_RCODE, 0xF);
		return RCode.valueOf(code); // TODO cache ?
	}

	/**
	 * an unsigned 16 bit integer specifying the number of entries in the
	 * question section.
	 */
	public int qdcount() {
		return this.qdcount;
	}

	public void qdcount(int value) {
		this.qdcount = value;
	}

	/**
	 * an unsigned 16 bit integer specifying the number of resource records in
	 * the answer section.
	 */
	public int ancount() {
		return this.ancount;
	}

	public void ancount(int value) {
		this.ancount = value;
	}

	/**
	 * an unsigned 16 bit integer specifying the number of name server resource
	 * records in the authority records section.
	 */
	public int nscount() {
		return this.nscount;
	}

	public void nscount(int value) {
		this.nscount = value;
	}

	/**
	 * an unsigned 16 bit integer specifying the number of resource records in
	 * the additional records section.
	 */
	public int arcount() {
		return this.arcount;
	}

	public void arcount(int value) {
		this.arcount = value;
	}
}