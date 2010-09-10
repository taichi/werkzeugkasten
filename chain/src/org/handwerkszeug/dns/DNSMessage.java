package org.handwerkszeug.dns;

import java.util.List;

/**
 * RFC1035 4. MESSAGES
 * 
 * @author taichi
 */
public interface DNSMessage {

	Header header();

	/**
	 * 4.1.2. Question section format
	 */
	List<ResourceRecord> question();

	List<ResourceRecord> answer();

	List<ResourceRecord> authority();

	List<ResourceRecord> additional();

}
