package werkzeugkasten.ecf.provider.twitter4j.container;

import org.eclipse.ecf.core.AbstractContainer;
import org.eclipse.ecf.core.ContainerConnectException;
import org.eclipse.ecf.core.events.ContainerConnectedEvent;
import org.eclipse.ecf.core.events.ContainerConnectingEvent;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.core.security.IConnectContext;

import twitter4j.AsyncTwitter;
import werkzeugkasten.ecf.provider.twitter4j.Constants;
import werkzeugkasten.ecf.provider.twitter4j.identity.TwitterID;

public class TwitterContainer extends AbstractContainer {

	protected ID localID;
	protected AsyncTwitter twitter;
	protected TwitterPresenceContainer presenceContainer;
	protected TwitterID targetID;

	public TwitterContainer(ID guid) {
		this.localID = guid;
		this.twitter = new AsyncTwitter(null, null);
		this.presenceContainer = new TwitterPresenceContainer(this,
				this.twitter);
	}

	@Override
	public void connect(ID targetID, IConnectContext connectContext)
			throws ContainerConnectException {
		if (targetID instanceof TwitterID) {
			this.targetID = (TwitterID) targetID;
		} else {
			throw new IllegalArgumentException("targetID " + targetID);
		}

		String password = getPasswordFromConnectContext(connectContext);
		fireContainerEvent(new ContainerConnectingEvent(localID, targetID,
				connectContext));
		this.twitter.setUserId(this.targetID.getUsername());
		this.twitter.setPassword(password);
		this.presenceContainer.populateRoster(this.targetID);
		fireContainerEvent(new ContainerConnectedEvent(this.localID,
				this.targetID));
		this.presenceContainer.readingStart();
	}

	@Override
	public void disconnect() {
		// do nothing
	}

	@Override
	public Namespace getConnectNamespace() {
		return IDFactory.getDefault()
				.getNamespaceByName(Constants.ID_NAMESPACE);
	}

	@Override
	public ID getConnectedID() {
		return this.targetID;
	}

	@Override
	public ID getID() {
		return this.localID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class serviceType) {
		if (serviceType.isInstance(this)) {
			return this;
		}
		return super.getAdapter(serviceType);
	}
}
