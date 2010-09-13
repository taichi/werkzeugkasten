package org.handwerkszeug.dns;

/**
 * RFC1035 4.1.1. Header section format
 */
public class Header {

	// include there flags |QR| Opcode |AA|TC|RD|RA| Z | RCODE|
	private int flags;

	/**
	 * A 16 bit identifier assigned by the program that generates any kind
	 * of query. This identifier is copied the corresponding reply and can
	 * be used by the requester to match up replies to outstanding queries.
	 * 
	 */
	public int id() {
		return 0;
	}

	/**
	 * A one bit field that specifies whether this message is a query (0),
	 * or a response (1).
	 */
	public boolean qr() {
		return false;
	}

	/**
	 * @see OpCode
	 */
	public OpCode opcode() {
		return null;
	}

	/**
	 * Authoritative Answer - this bit is valid in responses, and specifies
	 * that the responding name server is an authority for the domain name
	 * in question section.
	 */
	public boolean aa() {
		return false;
	}

	/**
	 * TrunCation - specifies that this message was truncated due to length
	 * greater than that permitted on the transmission channel.
	 */
	public boolean tc() {
		return false;
	}

	/**
	 * Recursion Desired - this bit may be set in a query and is copied into
	 * the response. If RD is set, it directs the name server to pursue the
	 * query recursively. Recursive query support is optional.
	 */
	public boolean rd() {
		return false;
	}

	/**
	 * Recursion Available - this be is set or cleared in a response, and
	 * denotes whether recursive query support is available in the name
	 * server.
	 */
	public boolean ra() {
		return false;
	}

	/**
	 * Reserved for future use. Must be zero in all queries and responses.
	 */
	public int z() {
		return 0;
	}

	/**
	 * @see RCode
	 */
	public RCode rcode() {
		return null;
	}

	/**
	 * an unsigned 16 bit integer specifying the number of entries in the
	 * question section.
	 */
	public int qdcount() {
		return 0;
	}

	/**
	 * an unsigned 16 bit integer specifying the number of resource records
	 * in the answer section.
	 */
	public int ancount() {
		return 0;
	}

	/**
	 * an unsigned 16 bit integer specifying the number of name server
	 * resource records in the authority records section.
	 */
	public int nscount() {
		return 0;
	}

	/**
	 * an unsigned 16 bit integer specifying the number of resource records
	 * in the additional records section.
	 */
	public int arcount() {
		return 0;
	}
}