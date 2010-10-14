package org.handwerkszeug.dns.server;

import org.handwerkszeug.dns.DNSClass;
import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.Type;

import werkzeugkasten.common.util.Validation;

public class DNSCacheKey {
	Name name;
	Type t;
	DNSClass c;

	public DNSCacheKey(Name name, Type t, DNSClass c) {
		super();
		Validation.notNull(name, "name");
		Validation.notNull(t, "t");
		Validation.notNull(c, "c");
		this.name = name;
		this.t = t;
		this.c = c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.c == null) ? 0 : this.c.hashCode());
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + ((this.t == null) ? 0 : this.t.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof DNSCacheKey) {
			return equals(DNSCacheKey.class.cast(other));
		}
		return false;
	}

	public boolean equals(DNSCacheKey other) {
		boolean ne = this.name.equals(other.name);

		boolean t = (this.t.equals(Type.ANY) || other.t.equals(Type.ANY) || this.t
				.equals(other.t));

		boolean c = (this.c.equals(DNSClass.ANY)
				|| other.c.equals(DNSClass.ANY) || this.c.equals(other.c));

		return ne && t && c;
	}
}