package org.handwerkszeug.dns.conf;

public interface MasterDataResource {

	void initialize();

	void process(MasterDataHandler processor);

	void dispose();
}
