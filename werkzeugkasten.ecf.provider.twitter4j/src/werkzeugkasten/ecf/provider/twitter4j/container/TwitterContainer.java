package werkzeugkasten.ecf.provider.twitter4j.container;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.ecf.core.AbstractContainer;
import org.eclipse.ecf.core.ContainerConnectException;
import org.eclipse.ecf.core.events.ContainerConnectedEvent;
import org.eclipse.ecf.core.events.ContainerConnectingEvent;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.ecf.core.util.ECFException;
import org.eclipse.ecf.presence.IIMMessageEvent;
import org.eclipse.ecf.presence.IIMMessageListener;
import org.eclipse.ecf.presence.chatroom.IChatRoomAdminListener;
import org.eclipse.ecf.presence.chatroom.IChatRoomAdminSender;
import org.eclipse.ecf.presence.chatroom.IChatRoomContainer;
import org.eclipse.ecf.presence.chatroom.IChatRoomMessageSender;
import org.eclipse.ecf.presence.chatroom.IChatRoomParticipantListener;
import org.eclipse.ecf.presence.im.ChatMessage;
import org.eclipse.ecf.presence.im.ChatMessageEvent;
import org.eclipse.ecf.presence.im.IChatMessage;
import org.eclipse.ecf.presence.im.IChatMessageEvent;
import org.eclipse.ecf.presence.im.IChatMessageSender;
import org.eclipse.ecf.presence.im.IChatMessage.Type;

import twitter4j.AsyncTwitter;
import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.User;
import werkzeugkasten.ecf.provider.twitter4j.Constants;
import werkzeugkasten.ecf.provider.twitter4j.identity.TwitterID;

public class TwitterContainer extends AbstractContainer implements
		IChatRoomContainer {

	protected ID localID;
	protected TwitterID targetID;
	protected List<IIMMessageListener> listeners = new Vector<IIMMessageListener>();

	protected IChatMessageSender privateMessageSender;
	protected IChatRoomMessageSender messageSender;
	protected AsyncTwitter twitter;
	protected Date since = new Date();

	public static final IChatMessage.Type Direct = new IChatMessage.Type(
			"direct") {
	};

	protected TwitterListener listener = new TwitterAdapter() {
		@Override
		public void sentDirectMessage(DirectMessage dm) {
			Namespace n = getConnectNamespace();
			IChatMessageEvent event = toMessage(n, dm);
			handleMessageEvent(event);
		}

		@Override
		public void gotDirectMessages(List<DirectMessage> messages) {
			Namespace n = getConnectNamespace();
			for (DirectMessage dm : messages) {
				IChatMessageEvent event = toMessage(n, dm);
				handleMessageEvent(event);
			}
		}

		@Override
		public void updated(Status s) {
			Namespace n = getConnectNamespace();
			IChatMessageEvent event = toMessage(n, s);
			handleMessageEvent(event);
		}

		@Override
		public void gotUserTimeline(List<Status> statuses) {
			Namespace n = getConnectNamespace();
			for (twitter4j.Status s : statuses) {
				IChatMessageEvent event = toMessage(n, s);
				handleMessageEvent(event);
			}
		}

		protected IChatMessageEvent toMessage(Namespace n, twitter4j.Status s) {
			return toMessage(n, s.getUser(), s.getText(),
					IChatMessage.Type.CHAT);
		}

		protected IChatMessageEvent toMessage(Namespace n,
				twitter4j.DirectMessage s) {
			return toMessage(n, s.getSender(), s.getText(), Direct);
		}

		protected IChatMessageEvent toMessage(Namespace n, twitter4j.User u,
				String txt, IChatMessage.Type type) {
			ID from = new TwitterID(n, u);
			IChatMessageEvent event = new ChatMessageEvent(from,
					new ChatMessage(from, type, null, txt, null));
			return event;
		}
	};

	public TwitterContainer(ID guid) {
		this.localID = guid;
		this.twitter = new AsyncTwitter(null, null);
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
		fireContainerEvent(new ContainerConnectedEvent(this.localID,
				this.targetID));

		this.privateMessageSender = new IChatMessageSender() {
			@SuppressWarnings("unchecked")
			@Override
			public void sendChatMessage(ID toID, ID threadID, Type type,
					String subject, String body, Map properties)
					throws ECFException {
				sendChatMessage(toID, body);
			}

			@Override
			public void sendChatMessage(ID toID, String body)
					throws ECFException {
				twitter.sendDirectMessageAsync(toID.getName(), body, listener);
			}
		};

		this.messageSender = new IChatRoomMessageSender() {
			@Override
			public void sendMessage(String message) throws ECFException {
				twitter.updateAsync(message, listener);
			}
		};
		this.twitter.getUserTimelineAsync(listener);
	}

	public void readTimeline() {
		synchronized (this.since) {
			this.twitter.getUserTimelineAsync(twitter.getUserId(), this.since,
					this.listener);
			this.since.setTime(System.currentTimeMillis());
		}
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

	public void handleMessageEvent(IIMMessageEvent messageEvent) {
		for (IIMMessageListener l : this.listeners) {
			l.handleMessageEvent(messageEvent);
		}
	}

	@Override
	public void addMessageListener(IIMMessageListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeMessageListener(IIMMessageListener listener) {
		this.removeMessageListener(listener);
	}

	@Override
	public IChatRoomMessageSender getChatRoomMessageSender() {
		return this.messageSender;
	}

	@Override
	public IChatMessageSender getPrivateMessageSender() {
		return this.privateMessageSender;
	}

	@Override
	public ID[] getChatRoomParticipants() {
		try {
			List<User> list = this.twitter.getFriends();
			List<ID> ids = new ArrayList<ID>(list.size());
			Namespace n = getConnectNamespace();
			for (User u : list) {
				ids.add(new TwitterID(n, u));
			}
			return ids.toArray(new ID[ids.size()]);
		} catch (TwitterException e) {
			// XXX ???
			throw new IllegalStateException(e);
		}
	}

	@Override
	public IChatRoomAdminSender getChatRoomAdminSender() {
		// do nothing ...
		return null;
	}

	@Override
	public void addChatRoomAdminListener(IChatRoomAdminListener adminListener) {
		// do nothing ...
	}

	@Override
	public void addChatRoomParticipantListener(
			IChatRoomParticipantListener participantListener) {
		// do nothing ...
	}

	@Override
	public void removeChatRoomAdminListener(IChatRoomAdminListener adminListener) {
		// do nothing ...
	}

	@Override
	public void removeChatRoomParticipantListener(
			IChatRoomParticipantListener participantListener) {
		// do nothing ...
	}
}
