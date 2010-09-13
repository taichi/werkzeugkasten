package org.handwerkszeug.dns;

public interface RRFactory {

	ResourceRecord create(int type);
}
