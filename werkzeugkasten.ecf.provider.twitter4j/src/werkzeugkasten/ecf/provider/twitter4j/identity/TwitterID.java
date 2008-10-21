package werkzeugkasten.ecf.provider.twitter4j.identity;

import org.eclipse.ecf.core.identity.BaseID;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.presence.im.IChatID;

public class TwitterID extends BaseID implements IChatID {

	private static final long serialVersionUID = -5464014141874282332L;
	protected String username;

	public TwitterID(Namespace namespace, String username) {
		super(namespace);
		this.username = username;
	}

	@Override
	protected int namespaceCompareTo(BaseID o) {
		return this.getName().compareTo(o.getName());
	}

	@Override
	protected boolean namespaceEquals(BaseID o) {
		if (o instanceof TwitterID) {
			TwitterID other = (TwitterID) o;
			return this.username.equals(other.username);
		}
		return false;
	}

	@Override
	protected String namespaceGetName() {
		return username;
	}

	@Override
	protected int namespaceHashCode() {
		return username.hashCode() ^ TwitterID.class.hashCode();
	}

	@Override
	public String getHostname() {
		return "twitter.com";
	}

	@Override
	public String getUsername() {
		return username;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class clazz) {
		if (clazz.isInstance(this)) {
			return this;
		}
		return super.getAdapter(clazz);
	}

	@Override
	public String toString() {
		return "TwitterID [" + username + "]";
	}
}
