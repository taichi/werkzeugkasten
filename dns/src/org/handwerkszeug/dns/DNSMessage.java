package org.handwerkszeug.dns;

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * RFC1035 4. MESSAGES
 * 
 * <pre>
 *     +---------------------+
 *     |        Header       |
 *     +---------------------+
 *     |       Question      | the question for the name server
 *     +---------------------+
 *     |        Answer       | RRs answering the question
 *     +---------------------+
 *     |      Authority      | RRs pointing toward an authority
 *     +---------------------+
 *     |      Additional     | RRs holding additional information
 *     +---------------------+
 * </pre>
 * 
 * @author taichi
 */
public class DNSMessage {

	protected Header header;
	protected List<ResourceRecord> question;
	protected List<ResourceRecord> answer;
	protected List<ResourceRecord> authority;
	protected List<ResourceRecord> additional;

	public DNSMessage() {
		this.header(new Header());
	}

	public DNSMessage(ChannelBuffer buffer) {
		this.header(new Header(buffer));
	}

	public void write(ChannelBuffer buffer) {
		header().write(buffer);
		NameCompressor nc = new SimpleNameCompressor();
		write(buffer, nc, question());
		write(buffer, nc, answer());
		write(buffer, nc, authority());
		write(buffer, nc, additional());
	}

	protected void write(ChannelBuffer buffer, NameCompressor compressor,
			List<ResourceRecord> list) {
		for (ResourceRecord rr : list) {
			rr.write(buffer, compressor);
		}
	}

	public Header header() {
		return this.header;
	}

	public void header(Header header) {
		this.header = header;
	}

	/**
	 * 4.1.2. Question section format
	 */
	public List<ResourceRecord> question() {
		return this.question;
	}

	public void question(List<ResourceRecord> list) {
		this.question = list;
	}

	public List<ResourceRecord> answer() {
		return this.answer;
	}

	public void answer(List<ResourceRecord> list) {
		this.answer = list;
	}

	public List<ResourceRecord> authority() {
		return this.authority;
	}

	public void authority(List<ResourceRecord> list) {
		this.authority = list;
	}

	public List<ResourceRecord> additional() {
		return this.additional;
	}

	public void additional(List<ResourceRecord> list) {
		this.additional = list;
	}
}
