package org.handwerkszeug.dns.record;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.Type;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 3.3.13. SOA RDATA format
 * 
 * <pre>
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * /                     MNAME                     /
 * /                                               /
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * /                     RNAME                     /
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                    SERIAL                     |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                    REFRESH                    |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                     RETRY                     |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                    EXPIRE                     |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                    MINIMUM                    |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * </pre>
 */
public class SOARecord extends AbstractRecord {

	/**
	 * The <domain-name> of the name server that was the original or primary
	 * source of data for this zone.
	 */
	protected Name mname;

	/**
	 * A <domain-name> which specifies the mailbox of the person responsible for
	 * this zone.
	 */
	protected Name rname;

	/**
	 * The unsigned 32 bit version number of the original copy of the zone. Zone
	 * transfers preserve this value. This value wraps and should be compared
	 * using sequence space arithmetic.
	 */
	protected long serial;

	/**
	 * A 32 bit time interval before the zone should be refreshed.
	 */
	protected long refresh;

	/**
	 * A 32 bit time interval that should elapse before a failed refresh should
	 * be retried.
	 */
	protected long retry;

	/**
	 * A 32 bit time value that specifies the upper limit on the time interval
	 * that can elapse before the zone is no longer authoritative.
	 */
	protected long expire;

	/**
	 * The unsigned 32 bit minimum TTL field that should be exported with any RR
	 * from this zone.
	 */
	protected long minimum;

	public SOARecord() {
		super(Type.SOA);
	}

	@Override
	protected void parseRDATA(ChannelBuffer buffer) {
		this.mname(new Name(buffer));
		this.rname(new Name(buffer));
		this.serial(buffer.readUnsignedInt());
		this.refresh(buffer.readUnsignedInt());
		this.retry(buffer.readUnsignedInt());
		this.expire(buffer.readUnsignedInt());
		this.minimum(buffer.readUnsignedInt());
	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		this.mname().write(buffer, compressor);
		this.rname().write(buffer, compressor);
		buffer.writeInt((int) this.serial());
		buffer.writeInt((int) this.refresh());
		buffer.writeInt((int) this.retry());
		buffer.writeInt((int) this.expire());
		buffer.writeInt((int) this.minimum());
	}

	public Name mname() {
		return this.mname;
	}

	public void mname(Name name) {
		this.mname = name;
	}

	public Name rname() {
		return this.rname;
	}

	public void rname(Name name) {
		this.rname = name;
	}

	public long serial() {
		return this.serial;
	}

	public void serial(long uint) {
		this.serial = uint & 0xFFFFFFFFL;
	}

	public long refresh() {
		return this.refresh;
	}

	public void refresh(long uint) {
		this.refresh = uint & 0xFFFFFFFFL;
	}

	public long retry() {
		return this.retry;
	}

	public void retry(long uint) {
		this.retry = uint & 0xFFFFFFFFL;
	}

	public long expire() {
		return this.expire;
	}

	public void expire(long uint) {
		this.expire = uint & 0xFFFFFFFFL;
	}

	public long minimum() {
		return this.minimum;
	}

	public void minimum(long uint) {
		this.minimum = uint & 0xFFFFFFFFL;
	}
}
